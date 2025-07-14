package com.fooddelivery.service;

import com.fooddelivery.entity.Restaurant;
import java.util.List;

public interface RestaurantService {
	Restaurant addRestaurant(Restaurant restaurant);

	List<Restaurant> getAllRestaurants();
}
