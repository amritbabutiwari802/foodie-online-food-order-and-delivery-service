package com.foodie.kitchenservice.dto;



import java.util.UUID;

public class CreateTicketDto {

    private UUID orderId;
    private UUID menuId;
    private int quantity;

    public CreateTicketDto() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
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

}
