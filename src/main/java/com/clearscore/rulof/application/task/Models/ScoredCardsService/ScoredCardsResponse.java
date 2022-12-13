package com.clearscore.rulof.application.task.Models.ScoredCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.pow;

/**
 * The response body received from the post to the ScoredCards API
 */
@Data
public class ScoredCardsResponse {
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
    public ScoredCardsResponse() {
    }

    /** Constructor */
    public ScoredCardsResponse(@NotNull String card, @NotNull double apr, @NotNull double approvalRating) {
        this.card = card;
        this.apr = apr;
        this.approvalRating = approvalRating;
    }

    /**
     * Used to get the card sorting order.
     * The response from the ScoredCards API is in a range of 0.0 - 10.0 so the eligibility
     * is first turned to a 0-100 scale.
     * sortingScore = eligibility • ((1/apr)²)
     * @return
     */
    public double getCardScore(){
        return approvalRating * 10 * (pow(1/apr,2));
    }

    /** this -> CreditCardResponse */
    public CreditCardResponse toCreditCardResponse(){
        return new CreditCardResponse("ScoredCards", card, apr, getCardScore());
    }
}
