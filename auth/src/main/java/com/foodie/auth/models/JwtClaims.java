package com.foodie.auth.models;

import com.foodie.auth.config.GrantedAuthority;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtClaims {
    private String principal;
    private List<GrantedAuthority> authorityList;
}
