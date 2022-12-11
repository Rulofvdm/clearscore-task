package com.clearscore.rulof.application.task.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the request body of the /creditcard endpoint
 */
@Data
public class CreditCardRequest {
    //region Properties
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Max(value = 700)
    @Min(value = 0)
    private Integer creditScore;

    @NotNull
    @Min(0)
    private Integer salary;
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
