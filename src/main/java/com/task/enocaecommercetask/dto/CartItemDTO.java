package com.task.enocaecommercetask.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private String productNameInCart;
    private int quantity;
    private double price;
}