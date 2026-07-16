package com.cwhite56.amtrec.domain.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellListDto {

    @NotEmpty
    @Size(max = 32)
    private String title; 

    @NotEmpty
    private String casterClass;
    
    @NotEmpty
    @Size(max = 32)
    private String user;

    @Size(max = 52, min = 43)
    private List<Integer> spentPoints;
    
}
