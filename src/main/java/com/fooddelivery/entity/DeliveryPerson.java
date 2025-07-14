package com.fooddelivery.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class DeliveryPerson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String phone;

	private boolean available = true; // true if not assigned

	@OneToMany(mappedBy = "deliveryPerson")
	private List<Order> assignedOrders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public List<Order> getAssignedOrders() {
		return assignedOrders;
	}

	public void setAssignedOrders(List<Order> assignedOrders) {
		this.assignedOrders = assignedOrders;
	}

}
