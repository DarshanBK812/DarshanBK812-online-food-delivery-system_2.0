package com.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.entity.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Integer> {

}
