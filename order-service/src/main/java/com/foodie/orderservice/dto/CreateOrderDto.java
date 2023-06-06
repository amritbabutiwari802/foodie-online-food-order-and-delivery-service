package com.foodie.orderservice.dto;


import java.util.UUID;

public class CreateOrderDto {

    private UUID restaurantId;
    private UUID branchID;
    private UUID menuId;
    private int quantity;

    public CreateOrderDto() {
    }

    public CreateOrderDto(UUID restaurantId, UUID branchID, UUID menuId, int quantity) {
        this.restaurantId = restaurantId;
        this.branchID = branchID;
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public UUID getBranchID() {
        return branchID;
    }

    public void setBranchID(UUID branchID) {
        this.branchID = branchID;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CreateOrderDto{" +
                "restaurantId=" + restaurantId +
                ", branchID=" + branchID +
                ", menuId=" + menuId +
                ", quantity=" + quantity +
                '}';
    }
}
