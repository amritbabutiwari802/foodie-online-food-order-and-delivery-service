package com.foodie.resturantservice.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID restaurantId;

    @Column(unique = true)
    private String email;

    @Column(unique=true)
    private String phoneNumber;

    // Default constructor
    public Branch() {
    }

    // Parameterized constructor
    public Branch(UUID restaurantId, String email, String phoneNumber) {
        this.restaurantId = restaurantId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

