package com.helpdesk.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.helpdesk.helpdesk.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

User findByUsernameAndPassword(String username,String password);

}