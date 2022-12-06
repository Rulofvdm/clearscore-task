package com.clearscore.rulof.application.task.Controllers;

import com.clearscore.rulof.application.task.Models.CreditCardRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/creditcards", method = POST)
public class CreditCards {

    public List<CreditCardRequest> getCreditCards(@RequestBody CreditCardRequest creditCardRequest ){
        System.out.println("Test");
        System.out.println(creditCardRequest.getName());
        return null;
    }
}
