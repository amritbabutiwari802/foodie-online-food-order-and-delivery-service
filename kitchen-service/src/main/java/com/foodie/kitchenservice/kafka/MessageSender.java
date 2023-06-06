package com.foodie.kitchenservice.kafka;

import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.CreateTicketDto;
import com.foodie.sagas.order.models.ResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final KafkaTemplate kafkaTemplate;

    public MessageSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createTicketResponse(ResponseDto responseDto) {
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RESPONSE, responseDto);
    }

    public void completeTicketResponse(ResponseDto responseDto) {
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_RESPONSE, responseDto);
    }

    public void compentiateTicketResponse(ResponseDto responseDto ) {
        this.kafkaTemplate.send(KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_RESPONSE, responseDto);
    }
}
