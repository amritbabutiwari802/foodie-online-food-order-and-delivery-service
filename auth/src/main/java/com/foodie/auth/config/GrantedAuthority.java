package com.foodie.auth.config;

public enum GrantedAuthority implements org.springframework.security.core.GrantedAuthority {
    CUSTOMER, RESTAURANT, DELIVERY, INTERNAL
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
