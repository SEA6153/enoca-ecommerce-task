package com.task.enocaecommercetask.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "customer_order")
@Getter
@Setter
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private double totalPrice;


}
