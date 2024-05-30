package com.example.esun.controller;

import com.example.esun.model.Order;
import com.example.esun.service.impl.OrderServiceImp;
import com.example.esun.dto.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderServiceImp orderService;

    @PostMapping("/users/{memberId}/order")
    public ResponseEntity<?> createOrder(@PathVariable String memberId,@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        
        Integer orderId = orderService.createOrder(memberId, createOrderRequest);
        return ResponseEntity.ok(orderService.createOrder(order));

    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders() {

        List<Order> orderList = orderService.getOrders(orderQueryParams);
        return ResponseEntity.ok(orderService.getAllOrders());

    }

    @PostMapping("/users/{memberId}/order")
    public List<OrderDetail> getOrderDetail(@PathVariable String memberId) {

        List<OrderDetail> orderDetail = orderService.getMemberOrder(memberId);
        return ResponseEntity.ok(orderService.createOrder(orderDetail));
        
    }
}
