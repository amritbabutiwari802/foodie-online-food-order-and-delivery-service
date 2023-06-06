package com.foodie.auth.dao;

import com.foodie.auth.entity.RestaurantAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantAuthDao extends JpaRepository<RestaurantAuth, UUID> {
    List<RestaurantAuth> findByEmail(String username);
}
