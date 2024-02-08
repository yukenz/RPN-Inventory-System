package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.mapper.ObjAutoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoMapperConfig {

    @Bean
    public ObjAutoMapper objectMapper() {
        return Mappers.getMapper(ObjAutoMapper.class);
    }
}
