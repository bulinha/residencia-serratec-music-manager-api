package br.org.serratec.mm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.org.serratec.mm.security.JwtAuthenticationFilter;
import br.org.serratec.mm.security.JwtAuthorizationFilter;
import br.org.serratec.mm.security.JwtUtil;
import br.org.serratec.mm.security.UsuarioDetalheService;
/***
 * Configuração de segurança da aplicação
 * Na versão mais atual do spring security a Classe WebSecurityConfigureAdapter ficou deprecated
 * Neste vídeo há um tutorial de como resolver isso https://www.youtube.com/watch?v=7HQ-x9aoZx8
 * @author bulinha
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioDetalheService usuarioDetalheService;

	@Autowired
	private JwtUtil jwtUtil;



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		String[] paths = { "/api/musica/**", "/api/album/**", "/api/artista/**" , "/" };
		String pathPlaylist = "/api/playlist/**";

		http
		.headers().frameOptions().disable().and()//para o h2
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/favicon.ico").permitAll()
			.antMatchers(HttpMethod.GET, paths).permitAll()
			.antMatchers(HttpMethod.POST, paths).hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.PUT, paths).hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.DELETE, paths).hasAuthority("ROLE_ADMIN")

			
			.antMatchers(HttpMethod.POST, "/api/playlist").hasAuthority("ROLE_USER")
			.antMatchers(HttpMethod.PUT, "/api/playlist").hasAuthority("ROLE_USER")
			.antMatchers(HttpMethod.DELETE, "/api/playlist").hasAuthority("ROLE_USER")
			.antMatchers(HttpMethod.GET, "/api/playlist").hasAuthority("ROLE_USER")

			.antMatchers(HttpMethod.POST, "/api/usuario").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/usuario").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/usuario").hasAuthority("ROLE_ADMIN")
			.antMatchers(HttpMethod.GET, "/api/usuario").hasAuthority("ROLE_ADMIN")

			.antMatchers(HttpMethod.POST,"/login").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/v2/api-docs/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()

			.anyRequest().authenticated()
			.and().exceptionHandling();
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, usuarioDetalheService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetalheService).passwordEncoder(passwordEncoder());
	}
	
//	@Bean
//	public CorsConfigurationSource corsConfiguratinSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		//configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//		//configuration.setAllowedHeaders(Arrays.asList("*"));
//		//configuration.setAllowCredentials(true);
//		//configuration.addExposedHeader("Authorization");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		
//		return source;
//	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
