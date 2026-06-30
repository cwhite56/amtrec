package com.cwhite56.amtrec.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cwhite56.amtrec.domain.User;
import com.cwhite56.amtrec.domain.UserDto;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(User a) {
        return modelMapper.map(a, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto b) {
        return modelMapper.map(b, User.class);
    }
    
}
