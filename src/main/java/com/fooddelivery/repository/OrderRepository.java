package com.fooddelivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser(User user);

}
