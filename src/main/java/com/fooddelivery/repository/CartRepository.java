package com.fooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Cart;
import com.fooddelivery.entity.Order;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	List<Order> findByUserId(int userId);
}
