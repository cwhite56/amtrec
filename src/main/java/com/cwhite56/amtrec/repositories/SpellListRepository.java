package com.cwhite56.amtrec.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cwhite56.amtrec.domain.Kingdom;
import com.cwhite56.amtrec.domain.entities.SpellList;
import com.cwhite56.amtrec.domain.entities.SpellListId;

@Repository
public interface SpellListRepository extends JpaRepository<SpellList, SpellListId>{

    List<SpellList> findAllByUserUsername(String username);

    List<SpellList> findByCasterClassAndUser_Kingdom(String casterClass, Kingdom kingdom);

    List<SpellList> findByCasterClass(String casterClass);
    
}