package com.clearscore.rulof.application.task.Models;

public class CreditCardRequest {
    private String name;
    private int creditScore;
    private int salary;

    public CreditCardRequest(String name, int creditScore, int salary) {
        this.name = name;
        this.creditScore = creditScore;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
