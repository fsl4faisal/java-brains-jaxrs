package org.faisal.javabrains.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MessageController {

    @GetMapping("/hello")
    public String showMessage() {
        return "Hi";
    }
}
