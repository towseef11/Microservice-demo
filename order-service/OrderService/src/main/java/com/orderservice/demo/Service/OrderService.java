package com.orderservice.demo.Service;

import org.springframework.stereotype.Service;

import com.orderservice.demo.Client.ProductClient;
import com.orderservice.demo.Model.CreateOrderRequest;
import com.orderservice.demo.Model.Order;
import com.orderservice.demo.Model.OrderItems;
import com.orderservice.demo.Model.OrderItemsRequest;
import com.orderservice.demo.Model.ProductResponse;
import com.orderservice.demo.Repository.OrderItemsRepository;
import com.orderservice.demo.Repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductClient productClient;

    public Order createOrder(CreateOrderRequest request, String email){
        Order order = new Order();
        order.setEmail(email);
        order.setAddress(request.getAddress());
        order.setOrderSuggestions(request.getOrderSuggestion());
        order.setPaymentMode(request.getPaymentMode());
        order.setStatus("Placed");

        double totalamount  = 0;

        for(OrderItemsRequest items : request.getItems() ){
            ProductResponse product = productClient.getProduct(items.getProductid());
            
            if(product.getAvailabilty()<=0)
                throw new RuntimeException(product.getName()+" is not available !");
            
            if(items.getQuantity() <= 0 )
                throw new RuntimeException("Quantity Must be greater than Zero");

            double subtotal = product.getPrice() * items.getQuantity();
            totalamount += subtotal; 

        }

        order.setAmount(totalamount);

        Order savedOrder = ordersRepository.save(order);

        for(OrderItemsRequest items : request.getItems()){
            ProductResponse product = productClient.getProduct(items.getProductid());

            OrderItems orderItems = new OrderItems();

            orderItems.setOrder(savedOrder);
            orderItems.setPrice(product.getPrice());
            orderItems.setProductId(product.getId());
            orderItems.setQuantity(items.getQuantity());

            orderItemsRepository.save(orderItems);
        }

        return savedOrder;

    }
    
}
