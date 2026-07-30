package com.cwhite56.amtrec.domain.dtos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "WIZARD|DRUID|BARD|HEALER")
    private String casterClass;
    
    @Size(max = 32)
    private String user;

    @Builder.Default
    private List<Integer> spentPoints = new ArrayList<>(Collections.nCopies(53, 0));

    @Builder.Default
    private List<Integer> pointsRemainingByLevel = new ArrayList<>(Collections.nCopies(6, 5));

    private String exp1;
    private String exp2;

    private boolean isLTP;
    private boolean isExp1;
    private boolean isExp2;
    
}
