package com.foodie.resturantservice.dao;

import com.foodie.resturantservice.entities.Address;
import com.foodie.resturantservice.entities.BlockedRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlockedRestaurantDao extends JpaRepository<BlockedRestaurant, UUID> {

}
