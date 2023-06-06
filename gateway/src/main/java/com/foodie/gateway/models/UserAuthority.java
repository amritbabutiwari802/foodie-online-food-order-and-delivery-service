package com.foodie.gateway.models;

public enum UserAuthority {
    CUSTOMER, RESTAURANT, DELIVERY, INTERNAL;
    String getAuthority(){
        return name();
    }
}
