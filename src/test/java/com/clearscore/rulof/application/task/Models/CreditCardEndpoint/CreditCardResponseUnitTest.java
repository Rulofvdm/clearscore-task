package com.clearscore.rulof.application.task.Models.CreditCardEndpoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreditCardResponseUnitTest {

    @Test
    void testCompareToSortOrder(){
        List<CreditCardResponse> creditCardResponses = new ArrayList<>();

        creditCardResponses.add(new CreditCardResponse("test provider 1", "test name 1", 20, 0.2));
        creditCardResponses.add(new CreditCardResponse("test provider 2", "test name 2", 30, 0.3));
        creditCardResponses.add(new CreditCardResponse("test provider 3", "test name 3", 10, 0.1));
        creditCardResponses.add(new CreditCardResponse("test provider 4", "test name 4", 40, 0.4));

        Collections.sort(creditCardResponses);
        System.out.println(creditCardResponses);

        assertEquals("test name 4", creditCardResponses.get(0).getName());
        assertEquals("test name 2", creditCardResponses.get(1).getName());
        assertEquals("test name 1", creditCardResponses.get(2).getName());
        assertEquals("test name 3", creditCardResponses.get(3).getName());
    }
}
