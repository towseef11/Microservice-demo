package com.orderservice.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table (name = "order_items")
@Data 
public class OrderItems {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne 
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private long productId;
    private int quantity;
    private double price;
}
