package com.foodie.customerservice.dao;

import com.foodie.customerservice.entities.BlockedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlockedAccountDao extends JpaRepository<BlockedAccount, Long> {
    public List<BlockedAccount> findByCustomerID(UUID customerId);

}
