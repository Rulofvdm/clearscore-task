package com.clearscore.rulof.application.task.Models.CSCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
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
    private String card;

    @NotNull
    @Min(0)
    @Max(100)
    private double apr;

    @NotNull
    @Min(0)
    @Max(1)
    private double approvalRating;

    /** Default constructor needed by the ObjectMapper.readValue function */
    public CSCardsResponse() {}

    /** Constructor */
    public CSCardsResponse(String card, double apr, double approvalRating) {
        this.card = card;
        this.apr = apr;
        this.approvalRating = approvalRating;
    }

    /**
     * Used to get the card sorting order.
     * The response from the CSCards API is in a range of 0.0 - 1.0 so the approvalRating
     * is first turned to a 0-100 scale.
     * sortingScore = eligibility • ((1/apr)²)
     * @return
     */
    private double getCardScore(){
        return approvalRating * 100 * (pow(1/apr,2));
    }

    /** this -> CreditCardResponse */
    public CreditCardResponse toCreditCardResponse(){
        return new CreditCardResponse("CSCards", card, apr, getCardScore());
    }
}
