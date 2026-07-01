package com.cwhite56.amtrec.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite56.amtrec.domain.dtos.SpellbookDto;
import com.cwhite56.amtrec.domain.entities.Spellbook;

@Component
public class SpellbookMapper implements Mapper<Spellbook, SpellbookDto> {

    private ModelMapper modelMapper;

    public SpellbookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SpellbookDto mapTo(Spellbook a) {
        SpellbookDto dto = modelMapper.map(a, SpellbookDto.class);

        dto.setUser(a.getUser().getUsername());

        return dto;
    }

    @Override
    public Spellbook mapFrom(SpellbookDto b) {
        return modelMapper.map(b, Spellbook.class);
    }
}