package com.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
