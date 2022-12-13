package com.clearscore.rulof.application.task.Models.CSCardsService;

import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CSCardsResponseUnitTests {

    @Test
    void getCardScoreTestExample1(){
        CSCardsResponse csCardsResponse = new CSCardsResponse("Test Card Name", 20, 5.5);

        // sortingScore = eligibility • 100 • ((1/apr)²)
        // 1.375 = 5.5 • 100 • ((1/20)²)
        assertEquals(137, Math.floor(csCardsResponse.getCardScore()*1000));
    }

    @Test
    void getCardScoreTestExample2(){
        CSCardsResponse csCardsResponse = new CSCardsResponse("Test Card Name", 21.4, 6.3);

        // sortingScore = eligibility • 100 • ((1/apr)²)
        // 1.37566599703031 = 6.3 • 100 • ((1/21.4)²)
        assertEquals(137, Math.floor(csCardsResponse.getCardScore()*1000));
    }

    @Test
    void toCreditCardResponse(){
        CSCardsResponse csCardsResponse = new CSCardsResponse("Donald", 21.4, 6.3);
        CreditCardResponse creditCardResponse = csCardsResponse.toCreditCardResponse();

        assertEquals("Donald", creditCardResponse.getName());
        assertEquals(21.4, creditCardResponse.getApr());
        assertEquals("CSCards", creditCardResponse.getProvider());
    }
}
