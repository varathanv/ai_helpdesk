package com.helpdesk.helpdesk.controller;


import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.helpdesk.helpdesk.model.User;
import com.helpdesk.helpdesk.repository.UserRepository;



@Controller
public class LoginController {



    private final UserRepository repo;



    public LoginController(UserRepository repo){

        this.repo = repo;

    }




    // Show login page
    @GetMapping("/login")
    public String login(){

        return "login";

    }





    // Check login
    @PostMapping("/login")
    public String checkLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session){



        User user = repo.findByUsernameAndPassword(
                username,
                password
        );



        if(user == null){

            return "redirect:/login?error=true";

        }



        // Store logged-in user
        session.setAttribute(
                "user",
                user
        );




        String role = user.getRole();



        if(role.equalsIgnoreCase("ADMIN")){


            return "redirect:/admin";


        }



        else if(role.equalsIgnoreCase("TECHNICIAN")){


            return "redirect:/technician";


        }



     else{


        return "redirect:/ticket";


        }

    }





    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session){


        session.invalidate();


        return "redirect:/login";


    }



}