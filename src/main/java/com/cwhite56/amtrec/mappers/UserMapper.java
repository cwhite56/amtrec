package com.cwhite56.amtrec.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite56.amtrec.domain.dtos.SpellListDto;
import com.cwhite56.amtrec.domain.dtos.UserDto;
import com.cwhite56.amtrec.domain.entities.User;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(User a) {
        UserDto dto = modelMapper.map(a, UserDto.class);

        for(SpellListDto spellList : dto.getSpellbook()) {
            spellList.setUser(a.getUsername());
        }
        
        return dto;
    }

    @Override
    public User mapFrom(UserDto b) {
        return modelMapper.map(b, User.class);
    }
    
}
