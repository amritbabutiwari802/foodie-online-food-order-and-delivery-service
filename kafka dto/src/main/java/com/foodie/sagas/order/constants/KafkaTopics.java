package com.foodie.sagas.order.constants;

public class KafkaTopics {
    public static  final String KITCHEN_SERVICE_CREATE_TICKET_REQUEST = "kitchen-service.create_order.request";
    public static final String KITCHEN_SERVICE_ROLLBACK_TICKET_REQUEST = "kitchen-service.rollback_order.request";
    public static final String CUSTOMER_SERVICE_VALIDATE_CUSTOMER_REQUEST = "customer-service.validate_customer.request";
    public static final String PAYMENT_SERVICE_PROCESS_PAYMENT_REQUEST = "payment-service.process_payment.request";
    public static final String KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_REQUEST = "kitchen-service.create_ticket.retriable.request";
    public static final String KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_REQUEST = "kitchen-service_create_ticket.compentiate.request";
    public static final String KITCHEN_SERVICE_CREATE_TICKET_RESPONSE = "kitchen-service.create_order.response";
    public static final String KITCHEN_SERVICE_ROLLBACK_TICKET_RESPONSE = "kitchen-service.rollback_order.response";
    public static final String CUSTOMER_SERVICE_VALIDATE_CUSTOMER_RESPONSE = "customer-service.validate_customer.response";
    public static final String PAYMENT_SERVICE_PROCESS_PAYMENT_RESPONSE = "payment-service.process_payment.response";
    public static final String KITCHEN_SERVICE_CREATE_TICKET_RETRIABLE_RESPONSE = "kitchen-service.create_ticket.retriable.response";
    public static final String KITCHEN_SERVICE_CREATE_TICKET_COMPENTIATE_RESPONSE = "kitchen-service_create_ticket.compentiate.response";
}
