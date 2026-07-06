package com.cwhite56.amtrec.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.entities.SpellList;

@Component
public class SpellListMapper implements Mapper<SpellList, SpellListDto>{

    private ModelMapper modelMapper;

    public SpellListMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SpellListDto mapTo(SpellList a) {
        SpellListDto dto = modelMapper.map(a, SpellListDto.class);
        dto.setUser(a.getUser().getUsername());
        return dto;
    }

    @Override
    public SpellList mapFrom(SpellListDto b) {
        return modelMapper.map(b, SpellList.class);
    }
    
}
