package com.foodie.customerservice.controller;

import com.foodie.customerservice.Dto.CreateCustomerDto;
import com.foodie.customerservice.config.Constants;
import com.foodie.customerservice.entities.Customer;
import com.foodie.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return this.customerService.createCustomer(createCustomerDto);
    }

    @PatchMapping("/update")
    public Customer updateCustomer(@RequestAttribute(Constants.PRINCIPAL) UUID customerId, @RequestBody CreateCustomerDto createCustomerDto) {
        return this.customerService.updateCustomer(customerId, createCustomerDto);

    }

    @PatchMapping("/block")
    public String blockCustomer(@RequestAttribute(Constants.PRINCIPAL) UUID customerId) {
        this.customerService.blockCustomer(customerId);
        return "blocked";
    }

    @PatchMapping("/unblock")
    public String unBlockAccount(@RequestAttribute(Constants.PRINCIPAL) UUID customerId) {
        this.customerService.unBlockAccount(customerId);
        return "unblocked";
    }


    @GetMapping("/is-customer-blocked")
    public boolean isCustomerBlocked(@RequestAttribute(Constants.PRINCIPAL) UUID customerId) {
        return this.customerService.isCustomerBlocked(customerId);
    }


}
