package com.learningMS.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity  //As this is a WebFlux project to configure security we have used @EnableWebFluxSecurity
public class SecurityConfig {

	//1. Usually in security we create a method that returns a Filter Chain but since this is a WebFlux 
		//We will return SecurityWeb 
	//2. Usually in security in the method, the parameter we have used is HttpSecurity but as this is WebFlux
		//We will be using ServerHttpSecurity
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
					.authorizeExchange()
					.anyExchange()
					.authenticated()
					.and()
					.oauth2Client()
					.and()
					.oauth2ResourceServer()
					.jwt();
		
		return httpSecurity.build();
	}
}
