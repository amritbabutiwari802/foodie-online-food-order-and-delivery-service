package com.foodie.resturantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateRestaurantDto {
    String name;
    String registrationNumber;
    String phoneNumber;
    String email;
    String password;
    String country;
    String province;
    String city;
    long postalCode;
    String localAddress;

}
