package com.foodie.deliveryservice.service;

import com.foodie.deliveryservice.entity.Delivery;
import com.foodie.deliveryservice.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService( DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery placeDeliveryOrder(UUID orderId, UUID addressId){
        return this.deliveryRepository.placeDeliveryOrder(orderId, addressId);
    }

    public Delivery cancelDelivery(UUID orderId){
        return this.deliveryRepository.cancelDelivery(orderId);
    }


    public Delivery updateDeliveryStatus(UUID orderId, Delivery.Status deliveryStatus){
        return this.deliveryRepository.updateDeliveryStatus(orderId,deliveryStatus);
    }

    public List<Delivery> getPlacedDelivery(){
        List<Delivery> deliveryList = this.deliveryRepository.getPlacedDelivery();
        return deliveryList;
    }


    public Delivery getDeliveryStatus(UUID orderId){
        return this.deliveryRepository.getDeliveryStatus(orderId);
    }

    public List<Delivery> getDeliveredOrders(){
        return this.deliveryRepository.getDeliveredOrders();
    }

    public boolean isDeliveryOrderPlaced(UUID orderId){
        return this.deliveryRepository.isDeliveryOrderPlaced(orderId);
    }

    public boolean isOrderDelivered(UUID orderId){
        return  this.isOrderDelivered(orderId);
    }

}
