package com.foodie.orderservice.repository;

import com.foodie.orderservice.dao.OrderDao;
import com.foodie.orderservice.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {

    private final OrderDao orderDao;

    public OrderRepository(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    public Order create(UUID customerId, UUID restaurantId, UUID branchId){
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setRestaurantId(restaurantId);
        order.setBranchId(branchId);
        return this.orderDao.save(order);
    }

    public Order updateOrderStatus(UUID orderId, Order.Status orderStatus){
        Optional<Order> optional = this.orderDao.findById(orderId);
        Order order = optional.get();
        order.setStatus(orderStatus);
        return this.orderDao.save(order);
    }

    public Order getOrder(UUID orderId){
        Optional<Order> optional = this.orderDao.findById(orderId);
        Order order = optional.get();
        return order;
    }
    public void rejectOrder(UUID orderId){
        Optional<Order> optional = this.orderDao.findById(orderId);
        Order order = optional.get();
        order.setStatus(Order.Status.REJECTED);
        this.orderDao.save(order);
    }

    public void approveOrder(UUID orderId){
        Optional<Order> optional = this.orderDao.findById(orderId);
        Order order = optional.get();
        order.setStatus(Order.Status.APPROVED);
        this.orderDao.save(order);
    }
    public boolean isOrderIdValid(UUID orderId){
        Optional<Order> optional = this.orderDao.findById(orderId);
        if(optional.isEmpty()){
            return false;
        }
        return true;
    }

}
