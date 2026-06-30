package com.cwhite56.amtrec.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cwhite56.amtrec.domain.Spellbook;
import com.cwhite56.amtrec.domain.SpellbookDto;

@Configuration
public class MapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Spellbook, SpellbookDto>() {
            @Override
            protected void configure() {
                skip(destination.getUser()); // Skips mapping back to UserDto parent
            }
        });
        return modelMapper;
    }
}