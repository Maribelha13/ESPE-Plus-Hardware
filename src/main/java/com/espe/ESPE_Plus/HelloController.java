package com.espe.ESPE_Plus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}