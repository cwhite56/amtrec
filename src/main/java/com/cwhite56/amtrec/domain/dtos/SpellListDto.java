package com.cwhite56.amtrec.domain.dtos;

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
    
    private UserDto user;

    private List<Integer> spentPoints;
    
}
