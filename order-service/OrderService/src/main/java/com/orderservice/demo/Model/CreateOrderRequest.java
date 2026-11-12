package com.orderservice.demo.Model;

import java.util.List;

import lombok.Data;

@Data 
public class CreateOrderRequest {

    private String address;
    private String orderSuggestion;
    private PaymentMode paymentMode;
    private List<OrderItemsRequest> items;
    
}
