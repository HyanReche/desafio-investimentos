package br.com.hyan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
			.title("Projeto API - BANCO JAVER")
			.description("API para gestão funcionalidades bancárias")
			.license(new License().name("Apache 2.0").url("http://www.apache.org/license/LICENSE-2.0"))
			.contact(new Contact().name("Hyan Reche").email("hyan.reche@gmail.com"))
		);
	}
}
