package com.cwhite56.amtrec.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellListDto {

    private String title; 
    
    private Spellbook spellbook;

    private List<Integer> spentPoints;
    
}
