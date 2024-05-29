package com.example.esun.controller;

import com.example.esun.model.Order;
import com.example.esun.service.impl.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderServiceImp orderService;

    @PostMapping("/users/{memerId}/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getOrders(orderQueryParams);
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
