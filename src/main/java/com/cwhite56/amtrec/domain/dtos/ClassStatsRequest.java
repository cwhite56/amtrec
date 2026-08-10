package com.cwhite56.amtrec.domain.dtos;

import com.cwhite56.amtrec.domain.Kingdom;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassStatsRequest {

    @Pattern(regexp = "WIZARD|DRUID|BARD|HEALER")
    private String casterClass;
    
    @Enumerated(EnumType.STRING)
    private Kingdom kingdom;
    
    @NotNull
    private Integer spellsPurchased; 
}
