package com.cwhite56.amtrec.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwhite56.amtrec.domain.entities.SpellList;

@Repository
public interface SpellListRepository extends JpaRepository<SpellList, String>{
    List<SpellList> findAllByUserUsername(String username);
    
}