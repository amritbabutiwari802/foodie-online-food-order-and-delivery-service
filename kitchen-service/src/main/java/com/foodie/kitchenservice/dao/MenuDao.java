package com.foodie.kitchenservice.dao;

import com.foodie.kitchenservice.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public  interface MenuDao extends JpaRepository<Menu, UUID> {
}
