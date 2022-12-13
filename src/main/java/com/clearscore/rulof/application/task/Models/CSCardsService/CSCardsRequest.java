package com.clearscore.rulof.application.task.Models.CSCardsService;

import lombok.Data;

/**
 * The request body sent to the CSCards API
 */
@Data
public class CSCardsRequest {
    private String name;
    private Integer creditScore;

    public CSCardsRequest(String name, Integer creditScore) {
        this.name = name;
        this.creditScore = creditScore;
    }
}
