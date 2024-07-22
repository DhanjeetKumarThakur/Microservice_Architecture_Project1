package com.learningMS.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learningMS.user.service.entity.Rating;
import com.learningMS.user.service.externalService.RatingService;

@SpringBootTest
class MicroservicesUserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is ok ok").build();
		Rating createRating = ratingService.createRating(rating);
		System.out.println("new Rating is created");
	}
}
