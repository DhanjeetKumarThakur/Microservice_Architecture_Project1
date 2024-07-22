package com.learningMS.user.service.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learningMS.user.service.entity.Hotel;
import com.learningMS.user.service.entity.Rating;
import com.learningMS.user.service.entity.User;
import com.learningMS.user.service.exception.ResourceNotFoundException;
import com.learningMS.user.service.externalService.HotelService;
import com.learningMS.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//It will generate Unique User ID
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		//fetch user from DB using userId
		 User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!"+userId));
		 
		 //fetch rating of the above user from RATING SERVICE
		 //http://localhost:8083/rating/users/fcd85691-046b-4738-a0a2-ce1c819bb981 
		 //How we can fetch the ratings of a specific user? How we will communicate with other Rest API
		 //This is done by a CLient (Http Client) that fetch the details from any service and this 
		 //can be done in many ways using Rest Template or by using FinClient 
		 //we will try to fetch using rest template
		 Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
		 logger.info("{}", ratingsOfUser);
		 
		 List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		 
		 List<Rating> ratingList = ratings.stream().map(rating -> {
			 //api call to hotel service to get the hotel
			 //http://localhost:8081/hotels/45e008cf-17b1-4439-98d9-6f626a71c6f3 --> hotels/hotelId
			 
			 //Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotels/45e008cf-17b1-4439-98d9-6f626a71c6f3", Hotel.class);
			 //we could also use "getForEntity"
			// ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			// Hotel hotel = forEntity.getBody();
			 
			 //Let's use Feign Client to hotek Service to get Hotel
			 Hotel hotel = hotelService.getHotel(rating.getHotelId());
			 
			 //set the hotel to rating
			 rating.setHotel(hotel);
			 //return the rating
			 return rating;
		 }).collect(Collectors.toList());
		 
		 //user.setRatings(ratingsOfUser);
		 user.setRatings(ratingList);
		 return user;
	}

}
