package com.helpdesk.helpdesk.controller;

import jakarta.servlet.http.HttpSession;
import com.helpdesk.helpdesk.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.helpdesk.helpdesk.repository.TicketRepository;




@Controller
public class TechnicianController {



    private final TicketRepository repository;



    public TechnicianController(TicketRepository repository){

        this.repository = repository;

    }




  @GetMapping("/technician")
public String technician(
        HttpSession session,
        Model model){

    User user = (User) session.getAttribute("user");

    if(user == null){

        return "redirect:/login";

    }

    model.addAttribute(
            "tickets",
            repository.findByAssignedTechnicianAndStatus(
                    user.getUsername(),
                    "IN PROGRESS"
            )
    );

    return "technician";

}





    @PostMapping("/technician/resolve")
    public String resolve(
            @RequestParam int id){



        var ticket = repository.findById(id).get();



        ticket.setStatus("RESOLVED");



        repository.save(ticket);



        return "redirect:/technician";


    }



}