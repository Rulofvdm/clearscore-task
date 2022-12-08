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
    @NotNull(message = "The request contained invalid parameters")
    @NotBlank(message = "The request contained invalid parameters")
    private String name;

    @NotNull(message = "The request contained invalid parameters")
    @Max(value = 700, message = "The request contained invalid parameters")
    @Min(value = 0, message = "The request contained invalid parameters")
    private int creditScore;

    @NotNull(message = "The request contained invalid parameters")
    @Min(value = 0, message = "The request contained invalid parameters")
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
