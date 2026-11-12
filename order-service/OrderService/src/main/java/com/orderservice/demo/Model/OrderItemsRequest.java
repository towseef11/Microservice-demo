package com.orderservice.demo.Model;

import lombok.Data;

@Data 
public class OrderItemsRequest {
    private long productid;
    private int quantity;
    private double price;
}
