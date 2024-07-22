package com.learningMS.hotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningMS.hotelService.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
