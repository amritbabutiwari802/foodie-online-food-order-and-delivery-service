package com.foodie.customerservice.services;

import com.foodie.customerservice.Dto.AuthRegisterRequestDto;
import com.foodie.customerservice.Dto.CreateAddressDto;
import com.foodie.customerservice.Dto.CreateCustomerDto;
import com.foodie.customerservice.config.Constants;
import com.foodie.customerservice.entities.Address;
import com.foodie.customerservice.entities.Customer;
import com.foodie.customerservice.repository.AddressRepository;
import com.foodie.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Value("${foodie.internal.secret}")
    private String internalSecret;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        boolean doesCustomerExistByEmailOrPhoneNumber = this.customerRepository
                .doesCustomerExistsByEmailOrPhoneNumber(createCustomerDto.getEmail(), createCustomerDto.getPhoneNumber());
        if (doesCustomerExistByEmailOrPhoneNumber) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user exists by Email or Phone number");
        }

        Customer customer = this.customerRepository.createCustomer(createCustomerDto);
        AuthRegisterRequestDto authRegisterRequestDto = new AuthRegisterRequestDto();
        authRegisterRequestDto.setCustomerId(customer.getId());
        authRegisterRequestDto.setEmail(customer.getEmail());
        authRegisterRequestDto.setPassword(createCustomerDto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.INTERNAL_SECRET, internalSecret );
        HttpEntity<AuthRegisterRequestDto> httpEntity = new HttpEntity<>(authRegisterRequestDto, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("lb://AUTH-SERVICE/api/auth/customer/register", HttpMethod.POST, httpEntity,String.class);
        return customer;
    }

    public Customer updateCustomer(UUID customerId, CreateCustomerDto createCustomerDto) {
        return this.customerRepository.updateCustomer(customerId, createCustomerDto);

    }

    public void blockCustomer(UUID customerId) {
        this.customerRepository.blockCustomer(customerId);
    }

    public void unBlockAccount(UUID customerId) {
        this.customerRepository.unBlockAccount(customerId);
    }



    public boolean isCustomerBlocked(UUID customerId) {
        return this.customerRepository.isCustomerBlocked(customerId);
    }

    public boolean isCustomerIdValid(UUID customerId) {
        return  this.customerRepository.isCustomerBlocked(customerId);
    }

    public boolean isCustomerAbleToPlaceOrder(UUID customerId){
        return !this.customerRepository.isCustomerBlocked(customerId);
    }


    //verify if a address belongs to a particular customer

}
