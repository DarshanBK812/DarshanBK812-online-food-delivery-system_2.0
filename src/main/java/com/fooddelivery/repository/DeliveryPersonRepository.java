package com.fooddelivery.repository;

import com.fooddelivery.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Integer> {
    Optional<DeliveryPerson> findFirstByAvailableTrue();
}
