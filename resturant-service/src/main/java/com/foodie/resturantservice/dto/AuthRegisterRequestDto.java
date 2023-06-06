package com.foodie.resturantservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AuthRegisterRequestDto {
    UUID restaurantId;
    String email;
    String password;
}
