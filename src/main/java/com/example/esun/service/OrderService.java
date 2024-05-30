package com.example.esun.service;

import com.example.esun.model.Order;
import com.example.esun.dto.CreateOrderRequest;
import com.example.esun.model.OrderDetail;

import java.util.List;

public interface OrderService {

    List<OrderDetail> getMemberOrder(String memberId, List<OrderDetail> orderDetailList);

    Integer createOrder(String orderId, Integer userId, CreateOrderRequest createOrderRequest);

    void createOrderDetail(String orderId, List<OrderDetail> orderDetailList);

    List<OrderDetail> getMemberOrder(String memberId);
}
