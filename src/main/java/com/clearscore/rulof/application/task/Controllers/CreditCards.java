package com.clearscore.rulof.application.task.Controllers;

import com.clearscore.rulof.application.task.Models.CreditCard;
import com.clearscore.rulof.application.task.Models.CreditCardRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreditCards {

    @PostMapping("/creditcards")
    public CreditCard getCreditCards(@Valid @RequestBody CreditCardRequest creditCardRequest ){
        System.out.println("Test");
        System.out.println(creditCardRequest);
        return new CreditCard("test provider", "test name", 5, 5);
    }
}