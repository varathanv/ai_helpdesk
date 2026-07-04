package com.helpdesk.helpdesk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.helpdesk.helpdesk.model.User;
import com.helpdesk.helpdesk.repository.UserRepository;



@Controller
public class RegisterController {



    private final UserRepository repository;



    public RegisterController(UserRepository repository){


        this.repository = repository;


    }





    @GetMapping("/register")
    public String registerPage(){


        return "register";


    }







    @PostMapping("/register")
    public String registerUser(User user){



        user.setRole("USER");



        repository.save(user);



        return "redirect:/login";


    }



}