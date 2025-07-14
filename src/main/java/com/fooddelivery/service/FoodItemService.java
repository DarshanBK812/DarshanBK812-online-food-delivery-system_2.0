package com.fooddelivery.service;

import com.fooddelivery.dto.FoodItemDTO;
import com.fooddelivery.entity.FoodItem;

import java.util.List;

public interface FoodItemService {
	FoodItem addFoodItem(FoodItemDTO foodItemDTO);

	List<FoodItem> getAllFoodItems();

	void updateStock(int id, int stock);

}
