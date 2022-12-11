package com.clearscore.rulof.application.task.Controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreditCardsIntegrationTest {

    private final static String URI = "/api/departments";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testOkValidation() throws Exception {
        String requestBody =
        """
        {
            "name" : "DAN",
            "creditScore" : 500,
            "salary" : 500
        }
        """;

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                .content(requestBody).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testEmptyName() throws Exception {
        String requestBody =
        """
        {
            "name" : "",
            "creditScore" : 500,
            "salary" : 500
        }
        """;

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                .content(requestBody).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testCreditScoreOutOfBoundsUnder() throws Exception {
        String requestBody =
        """
        {
            "name" : "Dan",
            "creditScore" : -1,
            "salary" : 500
        }
        """;

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                .content(requestBody).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testCreditScoreOutOfBoundsOver() throws Exception {
        String requestBody =
        """
        {
            "name" : "Dan",
            "creditScore" : 701,
            "salary" : 500
        }
        """;

        this.mockMvc
        .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                .content(requestBody).contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testSalaryOutOfBounds() throws Exception {
        String requestBody =
                """
                {
                    "name" : "Dan",
                    "creditScore" : 701,
                    "salary" : -1
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testTooMuchProperties() throws Exception {
        String requestBody =
                """
                {
                    "name" : "Dan",
                    "creditScore" : 701,
                    "salary" : -1,
                    "ThisIsAnUnnecessaryProperty": "five"
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }


    //Oh this test can promtly  be on the accepting end of a neener neener.
    //PS solved it with setting spring.jackson.deserialization.FAIL_ON_MISSING_CREATOR_PROPERTIES=true
    @Test
    public void testNotEnoughProperties() throws Exception {
        String requestBody =
                """
                {
                    "name" : "Dan"
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/creditcards", 1)
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}