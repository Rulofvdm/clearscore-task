package com.clearscore.rulof.application.task.Models.CreditCardEndpoint;

import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsRequest;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsRequest;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreditCardRequestUnitTests {


    @Test
    void toCSCardsRequestTest() {
        CreditCardRequest creditCardRequest = new @Valid CreditCardRequest("Dan", 500, 6000);
        CSCardsRequest csCardsRequest =  creditCardRequest.toCSCardsRequest();

        assertEquals(csCardsRequest.getName(), "Dan");
        assertEquals(csCardsRequest.getCreditScore(), 500);
    }

    @Test
    void toScoredCardsRequestTest() {
        CreditCardRequest creditCardRequest = new @Valid CreditCardRequest("Dan", 500, 6000);
        ScoredCardsRequest scoredCardsRequest  =  creditCardRequest.toScoredCardsRequest();

        assertEquals(scoredCardsRequest.getName(), "Dan");
        assertEquals(scoredCardsRequest.getScore(), 500);
        assertEquals(scoredCardsRequest.getSalary(), 6000);
    }
}
