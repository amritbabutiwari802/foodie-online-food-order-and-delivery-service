package com.foodie.orderservice.controller;

import com.foodie.orderservice.Service.OrderService;
import com.foodie.orderservice.dto.CreateOrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public boolean create(@RequestHeader("principal") String principal, @RequestBody()CreateOrderDto createOrderDto){
       return this.orderService.createOrder(UUID.fromString(principal), createOrderDto);

    }
}
