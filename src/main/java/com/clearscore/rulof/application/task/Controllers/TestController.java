package com.clearscore.rulof.application.task.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String GetTest(){
        return "Hello world!";
    }
}
