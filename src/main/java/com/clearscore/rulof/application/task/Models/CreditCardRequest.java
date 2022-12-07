package com.clearscore.rulof.application.task.Models;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CreditCardRequest {
    @NotNull
    private String name;
    @NotNull
    @Max(700)
    @Min(0)
    private int creditScore;
    @NotNull
    @Min(0)
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

    @Override
    public String toString() {
        return "CreditCardRequest{" +
                "name='" + name + '\'' +
                ", creditScore=" + creditScore +
                ", salary=" + salary +
                '}';
    }
}
