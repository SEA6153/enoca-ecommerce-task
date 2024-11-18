package com.task.enocaecommercetask.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Customer extends  BaseEntity{

    private String customerName;
    private String customerEmail;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Order> orderList;



}
