package com.learningMS.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningMS.user.service.entity.User;
import com.learningMS.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//here @RequestBody annotation is for deserialization (converting object into JSON)
	@PostMapping() //To trigge this url --> /users => Post Request
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	int retryCount = 1;
	
	//To trigge this url --> /users/{userId} => Get Request; here userId -> will be dynamic 
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Get Singer User Handler: UserController");
		logger.info("Retry Count: {}",retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}	
	
	
	//creating fall back method for circuit breaker
	//Always make sure the return type of fallback method and CircuitBreaker must be same.
	//And Parameter should be same ==> userId is in both methods 
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("Fallback is executed because service is down : ", ex.getMessage());
		User user = User.builder().email("dummy@gmail.com")
			.name("Dummy")
			.about("This user is created because some service is down")
			.userId("123456")
			.build();
		return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
	}
	
	//To trigge this url --> /users => Get Request
	@GetMapping()
	public ResponseEntity<List<User>> getUsers(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
}
