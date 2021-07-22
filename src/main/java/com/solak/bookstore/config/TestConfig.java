package com.solak.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.solak.bookstore.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaBaseDeDados() {
		this.dbService.instanciaBaseDeDados();
	}
	
	// quando 'starta' a aplicação, ele vai no application.properties vê a config do profile 
	// ativo sendo test, ele irá no perfil (TestConfig) e sobe o método instanciaBaseDeDados
	// e o @Bean instancia os objetos da classe DBService.

}
