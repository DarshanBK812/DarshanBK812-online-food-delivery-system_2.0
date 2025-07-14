package com.fooddelivery.controller;

import com.fooddelivery.dto.FoodItemDTO;
import com.fooddelivery.entity.FoodItem;
import com.fooddelivery.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooditem")
public class FoodItemController {

	@Autowired
	private FoodItemService foodItemService;

	@PostMapping
	public FoodItem addFoodItem(@RequestBody FoodItemDTO dto) {
		return foodItemService.addFoodItem(dto);
	}

	@GetMapping
	public List<FoodItem> getAll() {
		return foodItemService.getAllFoodItems();
	}
	
	@PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable int id, @RequestParam int stock) {
        foodItemService.updateStock(id, stock);
        return ResponseEntity.ok("Stock updated for item: " + id);
    }
}
