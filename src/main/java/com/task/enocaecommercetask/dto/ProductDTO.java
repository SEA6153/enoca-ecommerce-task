package com.task.enocaecommercetask.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductDTO {
    private String productName;
    private double productPrice;
    private int productStock;

}
