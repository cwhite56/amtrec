package com.cwhite56.amtrec.domain.dtos;

import java.util.List;

import com.cwhite56.amtrec.domain.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UserDto {

@NotEmpty
@Size(max = 32)    
private String username;

@NotEmpty
@Size(max = 2, min = 2)
private String kingdom;

@Enumerated(EnumType.STRING)
private Role role;

private List<SpellListDto> spellbook;



    
}