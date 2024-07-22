package com.learningMS.ratinglService.service;

import java.util.List;

import com.learningMS.ratinglService.entity.Rating;

public interface RatingService {

	//create 
	Rating create(Rating rating);
	
	//get all rating
	List<Rating> getRatings();
	
	//get all by user Id
	List<Rating> getRatingByUserId(String userId);
	
	//get all by Hotel Id
	List<Rating> getRatingByHotelId(String hotelId);
}
