package com.foodie.gateway.dto;

import com.foodie.gateway.models.UserAuthority;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class AuthenticationResponse {
    private UUID principal;
    private UserAuthority userAuthority;
}
