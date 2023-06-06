package com.foodie.auth.dao;

import com.foodie.auth.entity.CustomerAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerAuthDao extends JpaRepository<CustomerAuth, UUID> {
    List<CustomerAuth> findByEmail(String username);
}
