package com.clearscore.rulof.application.task.Models.CreditCardEndpoint;

import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsRequest;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the request body that we receive in the /creditcard endpoint
 */
@Data
public class CreditCardRequest {
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

    /** Constructor */
    public CreditCardRequest(String name, int creditScore, int salary) {
        this.name = name;
        this.creditScore = creditScore;
        this.salary = salary;
    }

    /** this -> CSCardsRequest */
    public CSCardsRequest toCSCardsRequest(){
        return new CSCardsRequest(name, creditScore, salary);
    }

    /** this -> ScoredCardsRequest */
    public ScoredCardsRequest toScoredCardsRequest(){
        return new ScoredCardsRequest(name, creditScore);
    }
}
