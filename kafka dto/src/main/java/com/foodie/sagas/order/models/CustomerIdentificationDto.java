package com.foodie.sagas.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerIdentificationDto {
    UUID customerid;
    UUID orderId;

}
