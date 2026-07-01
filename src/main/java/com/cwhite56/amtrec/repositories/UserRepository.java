package com.cwhite56.amtrec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwhite56.amtrec.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
}
