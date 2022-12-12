package com.clearscore.rulof.application.task.Models.CSCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.pow;

/**
 * The response body received from the post to the CSCards API
 */
@Data
public class CSCardsResponse {
    @NotEmpty
    @NotNull
    @JsonProperty("cardName")
    private String cardName;

    @NotNull
    @Min(0)
    @Max(100)
    @JsonProperty("apr")
    private double apr;

    @NotNull
    @Min(0)
    @Max(10)
    @JsonProperty("eligibility")
    private double eligibility;

    /** Default constructor needed by the ObjectMapper.readValue function */
    public CSCardsResponse() {}

    /** Constructor */
    public CSCardsResponse(@NotNull String cardName, @NotNull double apr, @NotNull double eligibility) {
        this.cardName = cardName;
        this.apr = apr;
        this.eligibility = eligibility;
    }

    /**
     * Used to get the card sorting order.
     * The response from the CSCards API is in a range of 0.0 - 1.0 so the approvalRating
     * is first turned to a 0-100 scale.
     * sortingScore = eligibility • ((1/apr)²)
     * @return
     */
    private double getCardScore(){
        return eligibility * 100 * (pow(1/apr,2));
    }

    /** this -> CreditCardResponse */
    public CreditCardResponse toCreditCardResponse(){
        return new CreditCardResponse("CSCards", cardName, apr, getCardScore());
    }
}
