package com.learningMS.user.service.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.learningMS.user.service.entity.Rating;

//@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	//get 
	
	//POST
	@PostMapping("/ratings")
	public Rating createRating(Rating rating);
	
	//PUT
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	
	//DELETE
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	

}
