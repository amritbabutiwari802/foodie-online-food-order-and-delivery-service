package com.foodie.kitchenservice.dao;

import com.foodie.kitchenservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketDao extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByOrderId(UUID orderid);
}
