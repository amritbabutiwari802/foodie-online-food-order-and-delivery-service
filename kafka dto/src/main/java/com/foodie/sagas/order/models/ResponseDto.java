package com.foodie.sagas.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    UUID orderId;
    SagaAction sagaAction;
}
