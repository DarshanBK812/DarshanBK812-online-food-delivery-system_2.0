package com.fooddelivery.serviceImpl;

import com.fooddelivery.dto.FoodItemDTO;
import com.fooddelivery.entity.FoodItem;
import com.fooddelivery.entity.Restaurant;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.FoodItemRepository;
import com.fooddelivery.repository.RestaurantRepository;
import com.fooddelivery.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private FoodItemRepository foodItemRepo;

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Override
	public FoodItem addFoodItem(FoodItemDTO dto) {
		Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId())
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

		FoodItem foodItem = new FoodItem();
		foodItem.setName(dto.getName());
		foodItem.setPrice(dto.getPrice());
		foodItem.setRestaurant(restaurant);

		return foodItemRepo.save(foodItem);
	}

	@Override
	public List<FoodItem> getAllFoodItems() {
		return foodItemRepo.findAll();
	}

	@Override
	public void updateStock(int id, int stock) {
		FoodItem item = foodItemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem id" + id));
		item.setStock(stock);
		foodItemRepo.save(item);
	}

}
