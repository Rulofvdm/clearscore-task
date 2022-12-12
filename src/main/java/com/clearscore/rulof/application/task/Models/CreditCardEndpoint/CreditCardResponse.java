package com.clearscore.rulof.application.task.Models.CreditCardEndpoint;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * A list of these objects is the response body for the /creditcards endpoint.
 */
@Data
public class CreditCardResponse implements Comparable{
    private String provider;
    private String name;
    private double apr;
    private double cardScore;

    /** Constructor */
    public CreditCardResponse(String provider, String name, double apr, double cardScore) {
        this.provider = provider;
        this.name = name;
        this.apr = apr;
        this.cardScore = cardScore;
    }

    /** getter needed for the compare to function */
    public double getCardScore() {
        return cardScore;
    }

    /**
     * Needed implementation of compareTo for the Comparable interface. Allows this object to be sorted.
     * In this case, if the object is sorted, it will be sorted by its cardScore property in descending order.
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(@NotNull Object o) {
        CreditCardResponse incomingCreditCardResponse = (CreditCardResponse)o;
        return (int)Math.round(cardScore -  incomingCreditCardResponse.getCardScore());
    }
}
