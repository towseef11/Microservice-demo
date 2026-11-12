package com.orderservice.demo.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderservice.demo.Model.ProductResponse;

@FeignClient (name = "product-service", 
             url = "http://localhost:8081")

public interface ProductClient {
    @GetMapping ("/Product/{id}")
    ProductResponse getProduct(@PathVariable long id);
}
