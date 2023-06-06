package com.foodie.orderservice.kafka;

import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.CreateTicketDto;
import com.foodie.sagas.order.models.CustomerIdentificationDto;
import com.foodie.sagas.order.models.ResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final KafkaTemplate kafkaTemplate;

    public MessageSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   public void validateCustomer(CustomerIdentificationDto customerIdentificationDto){
        this.kafkaTemplate.send(KafkaTopics.CUSTOMER_SERVICE_VALIDATE_CUSTOMER_REQUEST, customerIdentificationDto);
   }

   public void createTicket(CreateTicketDto createTicketDto){
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_REQUEST, createTicketDto);
   }

   public void processPayment(CustomerIdentificationDto customerIdentificationDto){
        this.kafkaTemplate.send(KafkaTopics.PAYMENT_SERVICE_PROCESS_PAYMENT_REQUEST, customerIdentificationDto);
   }

   public void approveTicket(CustomerIdentificationDto customerIdentificationDto){
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_REQUEST, customerIdentificationDto);
   }

   public void rejectTicket(CustomerIdentificationDto customerIdentificationDto){
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_REQUEST, customerIdentificationDto);
   }

}
