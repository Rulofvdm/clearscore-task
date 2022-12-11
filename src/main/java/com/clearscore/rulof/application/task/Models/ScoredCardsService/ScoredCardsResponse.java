package com.clearscore.rulof.application.task.Models.ScoredCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.pow;

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

    public ScoredCardsResponse(@NotNull String card, @NotNull double apr, @NotNull double approvalRating) {
        this.card = card;
        this.apr = apr;
        this.approvalRating = approvalRating;
    }

    private double getCardScore(){
        return approvalRating * 100 * (pow(1/apr,2));
    }

    public CreditCardResponse toCreditCardResponse(){
        return new CreditCardResponse("ScoredCards", card, apr, getCardScore());
    }
}
