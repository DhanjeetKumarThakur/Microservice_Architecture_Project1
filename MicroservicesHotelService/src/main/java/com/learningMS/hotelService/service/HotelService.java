package com.learningMS.hotelService.service;

import java.util.List;

import com.learningMS.hotelService.entity.Hotel;

public interface HotelService {

	//create
	Hotel create(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//get single 
	Hotel get(String id);
}
