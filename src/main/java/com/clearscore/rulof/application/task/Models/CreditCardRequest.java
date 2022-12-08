package com.clearscore.rulof.application.task.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents the request body of the /creditcard endpoint
 */
@Data
public class CreditCardRequest {
    //region Properties
    @NotNull()
    @NotBlank()
    private String name;

    @NotNull()
    @Max(value = 700)
    @Min(value = 0)
    private int creditScore;

    @NotNull()
    @Min(0)
    private int salary;
    //endregion

    /**
     * Constructor
     * @param name
     * @param creditScore
     * @param salary
     */
    public CreditCardRequest(String name, int creditScore, int salary) {
        this.name = name;
        this.creditScore = creditScore;
        this.salary = salary;
    }
}
