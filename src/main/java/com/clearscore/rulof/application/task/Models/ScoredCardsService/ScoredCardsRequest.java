package com.clearscore.rulof.application.task.Models.ScoredCardsService;

import lombok.Data;

/**
 * The request body sent to the ScoredCards API
 */
@Data
public class ScoredCardsRequest {
    private String name;
    private Integer creditScore;

    public ScoredCardsRequest(String name, Integer creditScore) {
        this.name = name;
        this.creditScore = creditScore;
    }
}
