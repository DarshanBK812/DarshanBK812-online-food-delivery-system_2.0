package com.fooddelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.FoodItem;
import com.fooddelivery.entity.Order;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
	
	


}
