package com.helpdesk.helpdesk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.helpdesk.helpdesk.service.AIService;


@Controller
public class ChatbotController {


    private final AIService aiService;


    public ChatbotController(AIService aiService){

        this.aiService = aiService;

    }



    @GetMapping("/chatbot")
    public String chatbot(){

        return "chatbot";

    }



    @PostMapping("/chatbot")
    public String ask(
            @RequestParam String message,
            Model model){


        String response =
                aiService.getSolution(message);


        model.addAttribute(
                "response",
                response
        );


        return "chatbot";

    }


}