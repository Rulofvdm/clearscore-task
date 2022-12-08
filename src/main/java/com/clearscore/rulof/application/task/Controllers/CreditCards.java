package com.clearscore.rulof.application.task.Controllers;

import com.clearscore.rulof.application.task.Models.CreditCard;
import com.clearscore.rulof.application.task.Models.CreditCardRequest;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCards {

    @PostMapping("/creditcards")
    public ResponseEntity<?> getCreditCards(@Valid @RequestBody CreditCardRequest creditCardRequest){
        CreditCard responseBody = new CreditCard("test provider", "test name", 5, 5);
        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Improper request parameters.");
    }
}