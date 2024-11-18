package com.task.enocaecommercetask.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private List<OrderItemDTO> items;
    private double totalPrice;
}