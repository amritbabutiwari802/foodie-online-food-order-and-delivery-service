package com.foodie.auth.models;

public enum UserAuthority {
    CUSTOMER, RESTAURANT, DELIVERY, INTERNAL;
    String getAuthority(){
        return name();
    }
}
