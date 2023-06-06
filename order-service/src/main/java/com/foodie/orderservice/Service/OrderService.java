package com.foodie.orderservice.Service;

import com.foodie.orderservice.dto.CreateOrderDto;
import com.foodie.orderservice.entity.Order;
import com.foodie.orderservice.kafka.MessageSender;
import com.foodie.orderservice.models.OrderState;
import com.foodie.orderservice.repository.OrderRepository;
import com.foodie.sagas.order.models.CreateTicketDto;
import com.foodie.sagas.order.models.CustomerIdentificationDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MessageSender messageSender;

    public OrderService(OrderRepository orderRepository, MessageSender messageSender) {
        this.orderRepository = orderRepository;
        this.messageSender = messageSender;
    }

    public boolean createOrder(UUID customerId, CreateOrderDto createOrderDto){
        Order order = this.orderRepository.create(customerId,createOrderDto.getRestaurantId(), createOrderDto.getBranchID());
        OrderState orderState = new OrderState();
        orderState.setOrderId(order.getId());
        orderState.setCustomerId(customerId);
        orderState.setMenuId(createOrderDto.getMenuId());
        orderState.setRestaurantId(createOrderDto.getRestaurantId());
        orderState.setBranchID(createOrderDto.getBranchID());
        orderState.setQuantity(createOrderDto.getQuantity());
        persistOrderState(order.getId(), orderState);
        CustomerIdentificationDto customerIdentificationDto = new CustomerIdentificationDto();
        customerIdentificationDto.setOrderId(order.getId());
        customerIdentificationDto.setCustomerid(customerId);
        this.messageSender.validateCustomer(customerIdentificationDto);
        return true;
    }

    public void rejectOrder(UUID orderId){
     this.orderRepository.rejectOrder(orderId);
    }

    public void approveOrder(UUID orderId){
        this.orderRepository.rejectOrder(orderId);
    }


    //get from cache
    public CustomerIdentificationDto getCustomerIdentification(UUID orderId){
        return new CustomerIdentificationDto();
    }

    // put on cache
    void persistOrderState(UUID orderId, OrderState orderState){

    }

    //get from cache
    public CreateTicketDto getCreateTicketDto(UUID orderId){
        return new CreateTicketDto();
    }
}
