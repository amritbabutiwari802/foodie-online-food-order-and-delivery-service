package com.foodie.customerservice.repository;

import com.foodie.customerservice.Dto.CreateAddressDto;
import com.foodie.customerservice.Dto.CreateCustomerDto;
import com.foodie.customerservice.dao.*;
import com.foodie.customerservice.entities.Address;
import com.foodie.customerservice.entities.BlockedAccount;
import com.foodie.customerservice.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {

    private final AddressDao addressDao;
    private final AuthDao authDao;
    private final BlockedAccountDao blockedAccountDao;
    private final CustomerDao customerDao;

    public CustomerRepository(AddressDao addressDao, AuthDao authDao, BlockedAccountDao blockedAccountDao, CustomerDao customerDao) {
        this.addressDao = addressDao;
        this.authDao = authDao;
        this.blockedAccountDao = blockedAccountDao;
        this.customerDao = customerDao;
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto){
        Customer customer = new Customer();
        customer.setName(createCustomerDto.getName());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
        customer.setGender(createCustomerDto.getGender());
        Customer created = this.customerDao.save(customer);
        BlockedAccount blockedAccount = new BlockedAccount();
        blockedAccount.setCustomerId(blockedAccount.getCustomerId());
        blockedAccount.setBlocked(false);
        this.blockedAccountDao.save(blockedAccount);
        return created;
    }

    public Customer updateCustomer(UUID customerId, CreateCustomerDto createCustomerDto){
        Optional<Customer> optional = this.customerDao.findById(customerId);
        Customer customer = optional.get();
        customer.setName(createCustomerDto.getName());
        customer.setEmail(createCustomerDto.getEmail());
        customer.setPhoneNumber(createCustomerDto.getPhoneNumber());
        customer.setGender(createCustomerDto.getGender());
        this.customerDao.save(customer);
        return customer;
    }

    public void blockCustomer(UUID customerId){
        List<BlockedAccount> blockedAccountList = this.blockedAccountDao.findByCustomerID(customerId);
        BlockedAccount blockedAccount = blockedAccountList.get(0);
        blockedAccount.setBlocked(true);
        this.blockedAccountDao.save(blockedAccount);
    }

    public void unBlockAccount(UUID customerId){
        List<BlockedAccount> blockedAccountList = this.blockedAccountDao.findByCustomerID(customerId);
        BlockedAccount blockedAccount = blockedAccountList.get(0);
        blockedAccount.setBlocked(false);
        this.blockedAccountDao.save(blockedAccount);
    }



    public boolean isCustomerBlocked(UUID customerId){
        List<BlockedAccount> blockedAccountList = this.blockedAccountDao.findByCustomerID(customerId);
        BlockedAccount blockedAccount = blockedAccountList.get(0);
        return blockedAccount.isBlocked();
    }

    public boolean isCustomerIdValid(UUID customerId){
        Optional<Customer> optional = this.customerDao.findById(customerId);
        if(optional.isEmpty()){
            return false;
        }

        return true;
    }

    public boolean doesCustomerExistsByEmailOrPhoneNumber(String email, long phoneNumber){
        List<Customer> customerList = this.customerDao.findByEmailOrPhoneNumber(email, phoneNumber);
        if(customerList.size()==0){
            return false;
        }

        return true;
    }
}
