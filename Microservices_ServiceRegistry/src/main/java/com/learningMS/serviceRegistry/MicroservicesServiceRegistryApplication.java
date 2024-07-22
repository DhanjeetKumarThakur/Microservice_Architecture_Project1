package com.learningMS.serviceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesServiceRegistryApplication.class, args);
	}

}
