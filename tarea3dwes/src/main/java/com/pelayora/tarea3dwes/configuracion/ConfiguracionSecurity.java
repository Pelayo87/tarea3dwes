package com.pelayora.tarea3dwes.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pelayora.tarea3dwes.repositorios.CredencialesRepository;

@Configuration
public class ConfiguracionSecurity {

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/", "/css/**", "/js/**", "/iniciosesion-registrarse").permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/iniciosesion-registrarse")
	                .loginProcessingUrl("/login")
	                .defaultSuccessUrl("/dashboard", true)
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/iniciosesion-registrarse")
	                .permitAll()
	            );

	        return http.build();
	    }
	    
	    @Bean
	    public UserDetailsService userDetailsService(CredencialesRepository credencialesRepository) {
	        return new UsuarioDetailsService(credencialesRepository);
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	}
