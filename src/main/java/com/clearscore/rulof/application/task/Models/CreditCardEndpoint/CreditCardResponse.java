package com.clearscore.rulof.application.task.Models.CreditCardEndpoint;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CreditCardResponse implements Comparable{
    private String provider;
    private String name;
    private double apr;
    private double cardScore;

    public CreditCardResponse(String provider, String name, double apr, double cardScore) {
        this.provider = provider;
        this.name = name;
        this.apr = apr;
        this.cardScore = cardScore;
    }

    public double getCardScore() {
        return cardScore;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        CreditCardResponse incomingCreditCardResponse = (CreditCardResponse)o;
        return (int)Math.round(cardScore -  incomingCreditCardResponse .getCardScore());
    }
}
