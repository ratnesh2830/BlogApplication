package com.api.BlogApplication.Config;

import com.api.BlogApplication.Entities.User;
import com.api.BlogApplication.Payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper()
    {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}