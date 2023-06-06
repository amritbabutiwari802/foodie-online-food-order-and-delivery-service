package com.foodie.deliveryservice.controller;


import com.foodie.deliveryservice.entity.Delivery;
import com.foodie.deliveryservice.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }


@GetMapping("/get-by-order-id/{order-id}")
    public Delivery getDeliveryStatus(@PathVariable("order-id") UUID orderId){
        return this.deliveryService.getDeliveryStatus(orderId);
    }


    @GetMapping("/get-delivered-orders")
    public List<Delivery> getDeliveredOrders(){
        return this.deliveryService.getDeliveredOrders();
    }

    @PatchMapping("/update-delivery-status/{order-id}")
    public Delivery updateDeliveryStatus(@PathVariable("order-id") UUID orderId, @RequestBody() Delivery.Status deliveryStatus){
    return this.deliveryService.updateDeliveryStatus(orderId, deliveryStatus);
    }

    @GetMapping("/get-placed-delivery")
    public List<Delivery> getPlacedDelivery(){
        return this.deliveryService.getPlacedDelivery();
    }

    @GetMapping("is-order-delivered")
    public boolean isOrderDelivered(UUID orderId){
        return  this.isOrderDelivered(orderId);
    }

}
