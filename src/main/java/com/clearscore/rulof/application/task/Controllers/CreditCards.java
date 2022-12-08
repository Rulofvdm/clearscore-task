package com.clearscore.rulof.application.task.Controllers;

import com.clearscore.rulof.application.task.Models.CreditCard;
import com.clearscore.rulof.application.task.Models.CreditCardRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CreditCards {

    private boolean invalidRequest(HttpServletRequest request){
        // if there is not 3 parameters
        System.out.println(request.getParameterMap().size());
        return request.getParameterMap().size() != 3;
    }

    @PostMapping("/creditcards")
    public ResponseEntity<?> getCreditCards(HttpServletRequest request, @Valid @RequestBody CreditCardRequest creditCardRequest ){
        if (invalidRequest(request)) return ResponseEntity.badRequest().build();

        System.out.println("Request body: " + creditCardRequest);

        CreditCard responseBody = new CreditCard("test provider", "test name", 5, 5);
        return ResponseEntity.ok(responseBody);
    }
}