package com.helpdesk.helpdesk.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.helpdesk.model.Ticket;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket,Integer>{


List<Ticket> findByEmail(String email);


List<Ticket> findByStatus(String status);


List<Ticket> findByPriority(String priority);



List<Ticket> findByNameContainingIgnoreCaseOrProblemContainingIgnoreCase(
String name,
String problem
);



List<Ticket> findByAssignedTechnician(String assignedTechnician);


List<Ticket> findByAssignedTechnicianAndStatus(
        String assignedTechnician,
        String status
);
}