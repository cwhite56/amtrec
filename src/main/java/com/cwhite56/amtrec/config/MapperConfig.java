package com.cwhite56.amtrec.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cwhite56.amtrec.domain.dtos.SpellbookDto;
import com.cwhite56.amtrec.domain.entities.Spellbook;

@Configuration
public class MapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}