package com.foodie.customerservice.services;

import com.foodie.customerservice.Dto.CreateAddressDto;
import com.foodie.customerservice.entities.Address;
import com.foodie.customerservice.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address addAddress(UUID customerId, CreateAddressDto createAddressDto) {
        return this.addressRepository.addAddress(customerId, createAddressDto);
    }

    public Address updateAddress(UUID customerId, UUID addressId, CreateAddressDto createAddressDto) {
        //check if a customerId has authority to update address.
        //this means if a address belongs to a particular customer
        verifyAddressBelongsToCustomer(customerId, addressId);
        return this.addressRepository.updateAddress(addressId, createAddressDto);
    }

    public Address channgeDefaultAddress(UUID customerId, UUID addressId) {
        //check if a customerId has authority to update address.
        //this means if a address belongs to a particular customer
        verifyAddressBelongsToCustomer(customerId, addressId);
        return this.addressRepository.channgeDefaultAddress(customerId, addressId);
    }

    public boolean deleteAddress(UUID customerId, UUID addressId) {
        //check if a customerId has authority to update address.
        //this means if a address belongs to a particular customer
        verifyAddressBelongsToCustomer(customerId, addressId);
        return this.addressRepository.deleteAddress(addressId);

    }

    void verifyAddressBelongsToCustomer(UUID customerId, UUID addressId){
        boolean doesCustomerHasAuthorityToUpdateAddress = this.addressRepository
                .matchCustomerIdAndAddressId(customerId, addressId);
        if(!doesCustomerHasAuthorityToUpdateAddress){
            // pass wrong addressId as error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong addressId");
        }
    }

}
