package com.foodie.customerservice.kafka;


import com.foodie.customerservice.services.CustomerService;
import com.foodie.sagas.order.constants.KafkaGroups;
import com.foodie.sagas.order.constants.KafkaTopics;
import com.foodie.sagas.order.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class MessageListner {
    public static  final Logger logger = LoggerFactory.getLogger(MessageListner.class);

    private final CustomerService customerService;
    private final MessageSender messageSender;

    public MessageListner(CustomerService customerService, MessageSender messageSender) {
        this.customerService = customerService;
        this.messageSender = messageSender;
    }

    @KafkaListener(topics = KafkaTopics.CUSTOMER_SERVICE_VALIDATE_CUSTOMER_REQUEST, groupId = KafkaGroups.CUSTOMER_SERVICE)
    public void validateCustomer(CustomerIdentificationDto customerIdentificationDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOrderId(customerIdentificationDto.getOrderId());
        boolean isCustomerValid= this.customerService.isCustomerIdValid(customerIdentificationDto.getCustomerid());
        if(isCustomerValid){
            boolean isCustomerBlocked = this.customerService.isCustomerBlocked(customerIdentificationDto.getCustomerid());
            if(isCustomerBlocked){
                responseDto.setSagaAction(SagaAction.ROLLBACK);
            }else{
                responseDto.setSagaAction(SagaAction.PROCEED);
            }
        }else{
            responseDto.setSagaAction(SagaAction.ROLLBACK);
        }
        this.messageSender.validateCustomerResponse(responseDto);
    }


}
