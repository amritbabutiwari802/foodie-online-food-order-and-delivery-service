package com.foodie.resturantservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "blocked_restaurant")
public class BlockedRestaurant {

    @Id
    UUID restaurantId;
    boolean blocked;

    public BlockedRestaurant() {
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "BlockedRestaurant{" +
                "restaurantId=" + restaurantId +
                ", blocked=" + blocked +
                '}';
    }
}
