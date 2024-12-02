package com.pelayora.tarea3dwes.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.pelayora.tarea3dwes.modelo")
@EnableJpaRepositories(basePackages = "com.pelayora.tarea3dwes.repositorios")
public class Tarea3dwesApplication {	
	
	@Bean
	public Principal applicationStartupRunner(){
		return new Principal();
	}

	public static void main(String[] args) {
		SpringApplication.run(Tarea3dwesApplication.class, args);
	}

}
