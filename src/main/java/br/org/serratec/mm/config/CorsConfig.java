package br.org.serratec.mm.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH","HEAD", "TRACE", "CONNECT")
		.allowedHeaders("Origin","Content-Type", "Authorization") 
		//.exposedHeaders("Authorization")
		
		;
	}

}
