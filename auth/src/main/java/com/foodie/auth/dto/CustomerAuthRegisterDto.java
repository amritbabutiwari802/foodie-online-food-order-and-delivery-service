package com.foodie.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CustomerAuthRegisterDto {
    private UUID customerId;
    private String email;
    private String password;
}
