package br.org.serratec.mm.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@SecurityScheme(name = "jwt_auth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class SwaggerConfig {
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	private SecurityContext securityContext() { 
	    return SecurityContext.builder()
	    		.securityReferences(defaultAuth())
	    		//.forPaths(PathSelectors.ant("/login/**"))
	    		.build(); 
	} 
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				//.apis(RequestHandlerSelectors.basePackage("br.org.serratec.mm.controller"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				;
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API Music Manager")
				.description("API de aplicativo para gerenciamento de Músicas da Serratec.")
				.termsOfServiceUrl("/service.html")
				.license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0%22")
				.version("1.0.0")
				.build();

		return apiInfo;
	}
	
	

}
