package com.foodie.customerservice.dao;

import com.foodie.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerDao extends JpaRepository<Customer, UUID> {
    List<Customer> findByEmailOrPhoneNumber(String email, long phoneNumber);

}
