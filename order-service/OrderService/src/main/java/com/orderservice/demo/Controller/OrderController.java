package com.orderservice.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.demo.Client.ProductClient;
import com.orderservice.demo.Model.ProductResponse;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/order")
@RequiredArgsConstructor
public class OrderController {
      
      private final ProductClient productClient;

      @GetMapping ("/Product/{id}")
      public ProductResponse getProductforResponse(@PathVariable int id){
            return productClient.getProduct(id);
      }


}
