package com.task.enocaecommercetask.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private List<CartItemDTO> items;
    private double totalPrice;
}
