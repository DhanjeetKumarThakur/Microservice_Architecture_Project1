package com.learningMS.hotelService.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class HotelStaffs {

	@GetMapping
	public ResponseEntity<List<String>> getAllStaffs(){
		List<String> stuffNames = Arrays.asList("Ram","Shyam", "Shanker");
		return new ResponseEntity<List<String>>(stuffNames, HttpStatus.OK);
	}
}
