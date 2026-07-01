package com.cwhite56.amtrec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwhite56.amtrec.domain.entities.Spellbook;

@Repository
public interface SpellbookRepository extends JpaRepository<Spellbook, String>{
    
}
