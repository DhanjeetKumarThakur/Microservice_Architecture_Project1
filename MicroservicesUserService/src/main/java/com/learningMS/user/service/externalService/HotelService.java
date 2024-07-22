package com.learningMS.user.service.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learningMS.user.service.entity.Hotel;

@FeignClient(name="HOTEL-SERVICE") //This is service name which is registered in Eureka Service Registry 
public interface HotelService {

	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
	//For the above method we don't have to provide the implementation, Spring Boot will provide by itself.
	//Such approach is called "Declarative Approach" 
}
