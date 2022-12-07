package com.clearscore.rulof.application.task.Models;

public class CreditCard {
    private String provider;
    private String name;
    private double apr;
    private double cardScore;

    public CreditCard(String provider, String name, double apr, double cardScore) {
        this.provider = provider;
        this.name = name;
        this.apr = apr;
        this.cardScore = cardScore;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public double getCardScore() {
        return cardScore;
    }

    public void setCardScore(double cardScore) {
        this.cardScore = cardScore;
    }
}
