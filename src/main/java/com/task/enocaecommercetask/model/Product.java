package com.task.enocaecommercetask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Product extends BaseEntity{

    private String productName;
    private Double productPrice;

    @Column(nullable = false)
    private int productStock;
}
