package com.foodie.orderservice.models;

import com.foodie.orderservice.dto.CreateOrderDto;

import java.util.UUID;

public class OrderState extends CreateOrderDto {
    UUID orderId;
    UUID customerId;

    public OrderState() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
