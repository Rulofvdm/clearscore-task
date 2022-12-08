package com.clearscore.rulof.application.task.Models;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Represents the request body of the /creditcard endpoint
 */
@Data
public class CreditCardRequest {
    //region Properties
    @NotBlank(message = "The request contained invalid parameters")
    private String name;

    @NotBlank(message = "The request contained invalid parameters")
    @Max(value = 700, message = "The request contained invalid parameters")
    @Min(value = 0, message = "The request contained invalid parameters")
    private int creditScore;

    @NotBlank(message = "The request contained invalid parameters")
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
