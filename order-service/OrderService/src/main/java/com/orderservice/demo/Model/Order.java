package com.orderservice.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name = "orders")
@Data 
public class Order {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private String email;
    private String orderSuggestions;
    private String address;
    private String status;
    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    
}
