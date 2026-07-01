package com.cwhite56.amtrec.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.User;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    private ModelMapper modelMapper;
    private SpellbookMapper spellbookMapper;

    public UserMapper(ModelMapper modelMapper, SpellbookMapper spellbookMapper) {
        this.modelMapper = modelMapper;
        this.spellbookMapper = spellbookMapper;
    }

    @Override
    public UserDto mapTo(User a) {
        UserDto dto = modelMapper.map(a, UserDto.class);

        if (a.getSpellbook() != null) {
            dto.setSpellbook(spellbookMapper.mapTo(a.getSpellbook()));
        }

        return dto;
    }

    @Override
    public User mapFrom(UserDto b) {
        return modelMapper.map(b, User.class);
    }
    
}
