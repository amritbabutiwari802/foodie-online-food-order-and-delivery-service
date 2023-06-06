package com.foodie.customerservice.dao;

import com.foodie.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressDao extends JpaRepository<Address, UUID> {

    List<Address> findByCustomerIdAndAddressType(UUID customerId, Address.AddressType addressType);



}
