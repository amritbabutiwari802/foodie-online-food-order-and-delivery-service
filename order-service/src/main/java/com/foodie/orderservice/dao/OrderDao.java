package com.foodie.orderservice.dao;

import com.foodie.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDao extends JpaRepository<Order, UUID> {
}
