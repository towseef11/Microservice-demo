package com.orderservice.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.demo.Model.Order;

public interface OrdersRepository extends JpaRepository<Order,Long> {
    
}
