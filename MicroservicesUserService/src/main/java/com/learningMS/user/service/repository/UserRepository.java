package com.learningMS.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningMS.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
