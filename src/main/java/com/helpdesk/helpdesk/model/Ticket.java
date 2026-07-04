package com.helpdesk.helpdesk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String problem;

    private String category;

    private String priority;

    private String status;

    private String assignedTeam;

    private String aiSuggestion;

    private String assignedTechnician;


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getProblem() {
        return problem;
    }


    public void setProblem(String problem) {
        this.problem = problem;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    public String getAssignedTeam(){

    return assignedTeam;

}


public void setAssignedTeam(String assignedTeam){

    this.assignedTeam = assignedTeam;

}
public String getAssignedTechnician(){

    return assignedTechnician;

}


public void setAssignedTechnician(String assignedTechnician){

    this.assignedTechnician = assignedTechnician;

}
public String getAiSuggestion(){

    return aiSuggestion;

}


public void setAiSuggestion(String aiSuggestion){

    this.aiSuggestion = aiSuggestion;

}

}