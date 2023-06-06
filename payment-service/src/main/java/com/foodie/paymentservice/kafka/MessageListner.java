package com.foodie.paymentservice.kafka;


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

    private final MessageSender messageSender;

    public MessageListner( MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @KafkaListener(topics = KafkaTopics.PAYMENT_SERVICE_PROCESS_PAYMENT_REQUEST, groupId = KafkaGroups.PAYMENT_SERVICE)
    public void processPayment(CustomerIdentificationDto customerIdentificationDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setOrderId(customerIdentificationDto.getOrderId());
      if(process()){
          responseDto.setSagaAction(SagaAction.PROCEED);
      }else{
          responseDto.setSagaAction(SagaAction.ROLLBACK);
      }
        this.messageSender.sendPaymentProcessResponse(responseDto);
    }

    //mock function to process payment. use realtime service like stripe in pro=oduction
    boolean process(){
        return true;
    }

}
