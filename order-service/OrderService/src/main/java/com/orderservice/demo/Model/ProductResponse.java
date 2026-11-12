package com.orderservice.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProductResponse {
    private int id;
    private String name;
    private double price;
    private int availabilty;
}
