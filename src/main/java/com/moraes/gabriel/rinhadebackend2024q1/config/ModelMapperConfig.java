package com.moraes.gabriel.rinhadebackend2024q1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
