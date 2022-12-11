package com.clearscore.rulof.application.task.Models.CSCardsService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class CSCardsRequest {
    private String name;
    private Integer creditScore;

    public CSCardsRequest(String name, Integer creditScore) {
        this.name = name;
        this.creditScore = creditScore;
    }
}
