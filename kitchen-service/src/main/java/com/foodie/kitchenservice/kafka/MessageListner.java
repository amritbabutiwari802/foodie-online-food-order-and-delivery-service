package com.foodie.kitchenservice.kafka;


import com.foodie.kitchenservice.service.KitchenService;
import com.foodie.sagas.order.constants.KafkaGroups;
import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.CreateTicketDto;
import com.foodie.sagas.order.models.OrderIdentificationDto;
import com.foodie.sagas.order.models.ResponseDto;
import com.foodie.sagas.order.models.SagaAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class MessageListner {
    public static  final Logger logger = LoggerFactory.getLogger(MessageListner.class);

    private final KitchenService kitchenService;
    private final MessageSender messageSender;

    public MessageListner(KitchenService kitchenService, MessageSender messageSender) {
        this.kitchenService = kitchenService;
        this.messageSender = messageSender;
    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_REQUEST, groupId = KafkaGroups.KITCHEN_SERVICE)
    public void createTicket(CreateTicketDto createTicketDto) {
        this.kitchenService.createTicket(createTicketDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOrderId(createTicketDto.getOrderId());
        responseDto.setSagaAction(SagaAction.PROCEED);
        this.messageSender.createTicketResponse(responseDto);
    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_REQUEST, groupId = KafkaGroups.KITCHEN_SERVICE)
    public void completeTicket(OrderIdentificationDto orderIdentificationDto) {
        this.kitchenService.approveTicket(orderIdentificationDto.getOrderId());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOrderId(orderIdentificationDto.getOrderId());
        responseDto.setSagaAction(SagaAction.PROCEED);
        this.messageSender.completeTicketResponse(responseDto);
    }

    @KafkaListener(topics = KafkaTopics.KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_REQUEST, groupId = KafkaGroups.KITCHEN_SERVICE)
    public void compentiateTicket(OrderIdentificationDto orderIdentificationDto ) {
        this.kitchenService.rejectTicket(orderIdentificationDto.getOrderId());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOrderId(orderIdentificationDto.getOrderId());
        responseDto.setSagaAction(SagaAction.PROCEED);
        this.messageSender.compentiateTicketResponse(responseDto);
    }

}
