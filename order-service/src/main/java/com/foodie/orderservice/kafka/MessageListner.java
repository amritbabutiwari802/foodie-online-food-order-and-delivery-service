package com.foodie.orderservice.kafka;

import com.foodie.orderservice.Service.OrderService;
import com.foodie.sagas.order.constants.KafkaGroups;
import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.CustomerIdentificationDto;
import com.foodie.sagas.order.models.ResponseDto;
import com.foodie.sagas.order.models.SagaAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class MessageListner {
    public static final Logger logger = LoggerFactory.getLogger(MessageListner.class);

    private final OrderService orderService;
    private final MessageSender messageSender;

    public MessageListner(OrderService orderService, MessageSender messageSender) {
        this.orderService = orderService;
        this.messageSender = messageSender;
    }

    @KafkaListener(topics = KafkaTopics.CUSTOMER_SERVICE_VALIDATE_CUSTOMER_RESPONSE, groupId = KafkaGroups.ORDER_SERVICE)
    public void validateCustomerResponse(ResponseDto responseDto) {
      if(responseDto.getSagaAction()== SagaAction.PROCEED){
          this.messageSender.createTicket(this.orderService.getCreateTicketDto(responseDto.getOrderId()));
      }else{
           this.orderService.rejectOrder(responseDto.getOrderId());
      }
    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RESPONSE, groupId = KafkaGroups.ORDER_SERVICE)
    public void createTicketResponse(ResponseDto responseDto) {
        //proceed to process payment
        if(responseDto.getSagaAction()==SagaAction.PROCEED){
            this.messageSender.processPayment(this.orderService.getCustomerIdentification(responseDto.getOrderId()));
        }
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_SERVICE_PROCESS_PAYMENT_RESPONSE, groupId = KafkaGroups.ORDER_SERVICE)
    public void processPaymentResponse(ResponseDto responseDto) {
        if(responseDto.getSagaAction()==SagaAction.PROCEED){
            this.messageSender.approveTicket(this.orderService.getCustomerIdentification(responseDto.getOrderId()));
        }else{
            this.messageSender.rejectTicket(this.orderService.getCustomerIdentification(responseDto.getOrderId()));
        }

    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_RESPONSE, groupId = KafkaGroups.ORDER_SERVICE)
    public void approveTicketResponse(ResponseDto responseDto) {
        //update orderstate to approved
        this.orderService.approveOrder(responseDto.getOrderId());
    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_RESPONSE, groupId = KafkaGroups.ORDER_SERVICE)
    public void rejectTicketResponse(ResponseDto responseDto) {
        // update orderstate to rejected
        this.orderService.rejectOrder(responseDto.getOrderId());
    }


}
