package com.helpdesk.helpdesk.controller;


import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.helpdesk.helpdesk.model.User;
import com.helpdesk.helpdesk.model.Ticket;
import com.helpdesk.helpdesk.repository.TicketRepository;
import com.helpdesk.helpdesk.service.AIService;



@Controller
public class TicketController {



    private final TicketRepository repository;

    private final AIService aiService;



    public TicketController(
            TicketRepository repository,
            AIService aiService){


        this.repository = repository;

        this.aiService = aiService;

    }





    // Employee ticket page

    @GetMapping("/ticket")
    public String home(){

        return "ticket";

    }





    // Save ticket with AI analysis

    @PostMapping("/save")
    public String save(
            Ticket ticket,
            HttpSession session){



        User user = (User) session.getAttribute("user");



        if(user == null){

            return "redirect:/login";

        }



        ticket.setEmail(user.getEmail());

        ticket.setName(user.getUsername());


        ticket.setStatus("OPEN");



        // AI Analysis

        String result = aiService.analyzeProblem(
                ticket.getProblem()
        );



        String[] aiData = result.split(",");



        ticket.setCategory(aiData[0]);

        ticket.setAssignedTeam(aiData[1]);

        ticket.setPriority(aiData[2]);

        ticket.setAiSuggestion(aiData[3]);



        repository.save(ticket);



        return "success";

    }







    // Admin dashboard

    @GetMapping("/admin")
    public String admin(
            @RequestParam(required=false) String filter,
            @RequestParam(required=false) String search,
            Model model){



        List<Ticket> tickets;



        if(search != null && !search.isEmpty()){



            tickets =
            repository.findByNameContainingIgnoreCaseOrProblemContainingIgnoreCase(
                    search,
                    search
            );


        }



        else if(filter == null || filter.equals("ALL")){


            tickets = repository.findAll();


        }



        else if(filter.equals("HIGH")){


            tickets = repository.findByPriority("HIGH");


        }



        else{


            tickets = repository.findByStatus(filter);


        }





        model.addAttribute(
                "tickets",
                tickets
        );



        model.addAttribute(
                "total",
                repository.count()
        );



        model.addAttribute(
                "open",
                repository.findByStatus("OPEN").size()
        );



        model.addAttribute(
                "resolved",
                repository.findByStatus("RESOLVED").size()
        );



        model.addAttribute(
                "high",
                repository.findByPriority("HIGH").size()
        );



        return "admin";

    }









    // Admin update status + assign technician

    @PostMapping("/update")
    public String updateStatus(
            @RequestParam int id,
            @RequestParam String status,
            @RequestParam String team,
            @RequestParam String technician){



        Ticket ticket =
        repository.findById(id).get();



        ticket.setStatus(status);



        ticket.setAssignedTeam(team);



        ticket.setAssignedTechnician(technician);



        repository.save(ticket);



        return "redirect:/admin";

    }



/* 





    // Resolve ticket

    @PostMapping("/resolve")
    public String resolve(
            @RequestParam int id){



        Ticket ticket =
        repository.findById(id).get();



        ticket.setStatus("RESOLVED");



        repository.save(ticket);



        return "redirect:/admin";

    }



*/





    // Employee my tickets

    @GetMapping("/mytickets")
    public String myTickets(
            Model model,
            HttpSession session){



        User user =
        (User) session.getAttribute("user");



        if(user == null){

            return "redirect:/login";

        }



        model.addAttribute(
                "tickets",
                repository.findByEmail(
                        user.getEmail()
                )
        );



        return "mytickets";

    }

}