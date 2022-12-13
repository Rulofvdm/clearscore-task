package com.clearscore.rulof.application.task.Models.ScoredCardsService;
import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ScoredCardsResponseUnitTests {

    @Test
    void getCardScoreTestExample1(){
        ScoredCardsResponse scoredCardsResponse = new ScoredCardsResponse("Test Card Name", 20, 0.5);

        // sortingScore = eligibility • 10 • ((1/apr)²)
        // 0.0125 = 0.5 • 10 • ((1/20)²)
        assertEquals(125, Math.floor(scoredCardsResponse.getCardScore()*1000));
    }

    @Test
    void getCardScoreTestExample2(){
        ScoredCardsResponse scoredCardsResponse = new ScoredCardsResponse("Test Card Name", 19.4, 0.8);

        // sortingScore = eligibility • 10 • ((1/apr)²)
        // 0.0212562440216814 = 0.8 • 10 • ((1/19.4)²)
        assertEquals(212, Math.floor(scoredCardsResponse.getCardScore()*1000));
    }

    @Test
    void toCreditCardResponse(){
        ScoredCardsResponse scoredCardsResponse = new ScoredCardsResponse("Daisy", 19.4, 0.8);
        CreditCardResponse creditCardResponse = scoredCardsResponse.toCreditCardResponse();

        assertEquals("Daisy", creditCardResponse.getName());
        assertEquals(19.4, creditCardResponse.getApr());
        assertEquals("ScoredCards", creditCardResponse.getProvider());
    }
}
