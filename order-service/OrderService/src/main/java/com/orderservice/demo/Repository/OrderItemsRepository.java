package com.orderservice.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.demo.Model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
    
}
