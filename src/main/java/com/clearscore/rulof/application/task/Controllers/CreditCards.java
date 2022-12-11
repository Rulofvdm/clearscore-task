package com.clearscore.rulof.application.task.Controllers;

import com.clearscore.rulof.application.task.Exceptions.CSCardsFailedException;
import com.clearscore.rulof.application.task.Exceptions.ScoredCardsFailedException;
import com.clearscore.rulof.application.task.Models.CSCardsService.CSCardsResponse;
import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardResponse;
import com.clearscore.rulof.application.task.Models.CreditCardEndpoint.CreditCardRequest;
import com.clearscore.rulof.application.task.Models.ScoredCardsService.ScoredCardsResponse;
import com.clearscore.rulof.application.task.Services.CSCards;
import com.clearscore.rulof.application.task.Services.ScoredCards;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CreditCards {

    @PostMapping("/creditcards")
    public ResponseEntity<?> getCreditCards(@Valid @RequestBody CreditCardRequest creditCardRequest){
        System.out.println("hello");
        List<CreditCardResponse> creditCardResponses = new ArrayList<>();
        try{
            CSCardsResponse[] csCardsResponses = CSCards.getCSCardsResponses(creditCardRequest.toCSCardsRequest());
            System.out.println("csCardsResponses length: " + csCardsResponses.length);
            creditCardResponses.addAll(Arrays.stream(csCardsResponses).map((x) -> x.toCreditCardResponse()).collect(Collectors.toList()));

            ScoredCardsResponse[] scoredCardsResponses = ScoredCards.getScoredCardsResponses(creditCardRequest.toScoredCardsRequest());
            System.out.println("scoredCardsResponses length: " + scoredCardsResponses.length);
            creditCardResponses.addAll(Arrays.stream(scoredCardsResponses).map((x) -> x.toCreditCardResponse()).collect(Collectors.toList()));
        } catch (CSCardsFailedException e) {
            return ResponseEntity.internalServerError().body("The CSCards service failed");
        } catch (ScoredCardsFailedException e) {
            return ResponseEntity.internalServerError().body("The ScoredCards service failed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(creditCardResponses);
        return ResponseEntity.ok(creditCardResponses);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Improper request parameters.");
    }
}