package com.foodie.deliveryservice.dao;

import com.foodie.deliveryservice.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryDao extends JpaRepository<Delivery, UUID> {
    List<Delivery> findByOrderId(UUID orderId);
    List<Delivery> findByStatus(Delivery.Status status);


}
