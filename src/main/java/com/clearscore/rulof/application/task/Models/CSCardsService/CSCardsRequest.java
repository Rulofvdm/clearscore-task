package com.clearscore.rulof.application.task.Models.CSCardsService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * The request body sent to the CSCards API
 */
@Data
public class CSCardsRequest {
    private String name;
    private Integer score;
    private Integer salary;

    public CSCardsRequest(String name, Integer score, Integer salary) {
        this.name = name;
        this.score = score;
        this.salary = salary;
    }
}
