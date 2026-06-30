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
public class SpellbookDto {

private String id;

private String user;

private List<SpellListDto> spellListCollection;



    
}
