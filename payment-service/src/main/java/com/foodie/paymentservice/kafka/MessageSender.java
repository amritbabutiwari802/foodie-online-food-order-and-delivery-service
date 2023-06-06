package com.foodie.paymentservice.kafka;

import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.ResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final KafkaTemplate kafkaTemplate;

    public MessageSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendPaymentProcessResponse(ResponseDto responseDto) {
        this.kafkaTemplate.send(KafkaTopics.PAYMENT_SERVICE_PROCESS_PAYMENT_RESPONSE, responseDto);
    }
}
