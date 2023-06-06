package com.foodie.customerservice.Dto;

import com.foodie.customerservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCustomerDto {
    private String name;
    private String email;
    private long phoneNumber;

    private String password;
    private Customer.Gender gender;

    // Default constructor

}
