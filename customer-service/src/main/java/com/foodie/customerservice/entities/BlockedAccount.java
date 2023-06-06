package com.foodie.customerservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.UUID;

@Entity
public class BlockedAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private UUID customerId;

    private boolean blocked;

    // Default constructor
    public BlockedAccount() {
    }

    // Parameterized constructor
    public BlockedAccount(UUID customerId, boolean blocked) {
        this.customerId = customerId;
        this.blocked = blocked;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "BlockedAccount{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", blocked=" + blocked +
                '}';
    }
}
