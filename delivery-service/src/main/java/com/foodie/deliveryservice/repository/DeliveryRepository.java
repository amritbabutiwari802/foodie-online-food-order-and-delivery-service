package com.foodie.deliveryservice.repository;

import com.foodie.deliveryservice.dao.DeliveryDao;
import com.foodie.deliveryservice.entity.Delivery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DeliveryRepository {
    private final DeliveryDao deliveryDao;

    public DeliveryRepository(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

   public Delivery placeDeliveryOrder(UUID orderId, UUID addressId){
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setAddressId(addressId);
        delivery.setStatus(Delivery.Status.PLACED);
        return this.deliveryDao.save(delivery);
    }

    public Delivery cancelDelivery(UUID orderId){
        List<Delivery> deliveryList = this.deliveryDao.findByOrderId(orderId);
        Delivery delivery = deliveryList.get(0);
        delivery.setStatus(Delivery.Status.CANCELLED);
        return this.deliveryDao.save(delivery);
    }

    public Delivery getDeliveryStatus(UUID orderId){
        List<Delivery> deliveryList = this.deliveryDao.findByOrderId(orderId);
        return deliveryList.get(0);
    }

    public Delivery updateDeliveryStatus(UUID orderId, Delivery.Status deliveryStatus){
        List<Delivery> deliveryList = this.deliveryDao.findByOrderId(orderId);
        Delivery delivery = deliveryList.get(0);
        delivery.setStatus(deliveryStatus);
        return this.deliveryDao.save(delivery);
    }

    public List<Delivery> getPlacedDelivery(){
        List<Delivery> deliveryList = this.deliveryDao.findByStatus(Delivery.Status.PLACED);
        return deliveryList;
    }

    public List<Delivery> getDeliveredOrders(){
        return this.deliveryDao.findByStatus(Delivery.Status.DELIVERED);
    }

    public boolean isDeliveryOrderPlaced(UUID orderId){
        List<Delivery> deliveryList = this.deliveryDao.findByOrderId(orderId);
        if(deliveryList.size()==0){
            return false;

        }
        return true;
    }

    public boolean isOrderDelivered(UUID orderId){
        List<Delivery> deliveryList = this.deliveryDao.findByOrderId(orderId);
        Delivery delivery = deliveryList.get(0);
        if(delivery.getStatus()==Delivery.Status.DELIVERED){
            return true;
        }
        return false;
    }

}
