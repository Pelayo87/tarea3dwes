package com.pelayora.tarea3dwes.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSecurity {
    
    @Autowired
    @Lazy
    private ServicioDetallesUsuario S_detallesUsuario;
    
    @Autowired
    private RedireccionUsuarioAutenticacionExitosa redireccionautenticaionExitosa;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(S_detallesUsuario);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/inicio", "/login", "/iniciosesion-registrarse", "/registro", "/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/inicio-admin","/plantas-admin", "/plantas-adminAñadir", 
                	              "/plantas-adminModificar", "/plantas-adminEliminar","/anadirpersonal")
                .hasRole("ADMIN")
                .requestMatchers("/inicio-personal", "/gestion-pedidos")
                .hasRole("PERSONAL")
                .requestMatchers("/inicio-cliente", "/mispedidos", "/carrito-compra", "/factura").hasRole("CLIENTE")
                .requestMatchers("/gestion-ejemplares", "/gestion-fitosanitarios","gestion-mensajes").hasAnyRole("ADMIN", "PERSONAL")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("usuario")
                .passwordParameter("contrasena")
                .successHandler(redireccionautenticaionExitosa)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/inicio?logout=true")
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/error-403"));

        return http.build();
    }


}
