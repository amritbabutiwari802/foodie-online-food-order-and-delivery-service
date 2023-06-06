package com.foodie.resturantservice.dao;

import com.foodie.resturantservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressDao extends JpaRepository<Address, UUID> {
    List<Address> findByRestaurantIdAndBranchId(UUID restaurantId, UUID barnchId);
}
