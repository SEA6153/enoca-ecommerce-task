package com.task.enocaecommercetask.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Cart  extends BaseEntity{

    @OneToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();


    private double totalPrice;

}
