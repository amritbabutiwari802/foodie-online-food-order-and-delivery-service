package com.foodie.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RestaurentAuthRegisterDto {
    UUID restaurantId;
    UUID branchId;
    String email;
    String password;
}
