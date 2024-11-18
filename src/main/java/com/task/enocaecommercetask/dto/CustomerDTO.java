package com.task.enocaecommercetask.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CustomerDTO {
    private Long id;
    private String customerName;
    private String customerEmail;
}
