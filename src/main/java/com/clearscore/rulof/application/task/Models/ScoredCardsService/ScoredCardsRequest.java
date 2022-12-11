package com.clearscore.rulof.application.task.Models.ScoredCardsService;

import lombok.Data;

@Data
public class ScoredCardsRequest {
    private String name;
    private Integer score;
    private Integer salary;

    public ScoredCardsRequest(String name, Integer score, Integer salary) {
        this.name = name;
        this.score = score;
        this.salary = salary;
    }
}
