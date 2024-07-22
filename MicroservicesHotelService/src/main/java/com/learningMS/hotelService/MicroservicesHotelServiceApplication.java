package com.learningMS.hotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesHotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesHotelServiceApplication.class, args);
	}

}
