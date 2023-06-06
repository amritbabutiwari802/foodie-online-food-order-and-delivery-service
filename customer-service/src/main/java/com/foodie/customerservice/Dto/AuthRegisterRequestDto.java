package com.foodie.customerservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRegisterRequestDto {
    private UUID customerId;
    private String email;
    private String password;
}
