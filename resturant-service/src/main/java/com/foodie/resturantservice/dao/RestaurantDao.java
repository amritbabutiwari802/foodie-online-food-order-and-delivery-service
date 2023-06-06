package com.foodie.resturantservice.dao;

import com.foodie.resturantservice.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantDao extends JpaRepository<Restaurant, UUID> {
    List<Restaurant> findByRegistrationNumber(String registrationNumber);
}
