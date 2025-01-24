package br.com.hyan.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteMapper {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
