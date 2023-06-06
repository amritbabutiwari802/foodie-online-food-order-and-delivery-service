package com.foodie.customerservice.controller;

import com.foodie.customerservice.Dto.CreateAddressDto;
import com.foodie.customerservice.config.Constants;
import com.foodie.customerservice.entities.Address;
import com.foodie.customerservice.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AddressController {
    
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/create")
    public Address addAddress(@RequestAttribute(Constants.PRINCIPAL) UUID customerId, @RequestBody() CreateAddressDto createAddressDto) {
        return this.addressService.addAddress(customerId, createAddressDto);
    }

    @PatchMapping("/update/{address-id}")
    public Address updateAddress(@RequestAttribute(Constants.PRINCIPAL) UUID customerId,
                                 @PathVariable("address-id") UUID addressId,@RequestBody() CreateAddressDto createAddressDto) {
        return this.addressService.updateAddress(customerId,addressId, createAddressDto);
    }

    @PatchMapping("/change-default-address/{address-id}")
    public Address channgeDefaultAddress(@RequestAttribute(Constants.PRINCIPAL) UUID customerId, @PathVariable("address-id") UUID addressId) {
        return this.addressService.channgeDefaultAddress(customerId, addressId);
    }

    @DeleteMapping("/delete/{address-id}")
    public boolean deleteAddress(@RequestAttribute(Constants.PRINCIPAL) UUID customerId, @PathVariable("address-id") UUID addressId) {
        return this.addressService.deleteAddress(customerId,addressId);

    }    
    
}
