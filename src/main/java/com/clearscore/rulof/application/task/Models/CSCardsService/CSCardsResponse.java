package com.clearscore.rulof.application.task.Models.CSCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.pow;

@Data
public class CSCardsResponse {
    @NotEmpty
    @NotNull
    private String cardName;

    @NotNull
    @Min(0)
    @Max(100)
    private double apr;

    @NotNull
    @Min(0)
    @Max(10)
    private double eligibility;

    public CSCardsResponse(@NotNull String cardName, @NotNull double apr, @NotNull double eligibility) {
        this.cardName = cardName;
        this.apr = apr;
        this.eligibility = eligibility;
    }

    private double getCardScore(){
        return eligibility * 10 * (pow(1/apr,2));
    }

    public CreditCardResponse toCreditCardResponse(){
        return new CreditCardResponse("CSCards", cardName, apr, getCardScore());
    }
}
