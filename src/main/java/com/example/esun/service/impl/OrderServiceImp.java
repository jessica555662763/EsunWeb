package com.example.esun.service.impl;


import com.example.esun.model.Order;
import com.example.esun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getProducts() {

        return orderRepository.findAll();
    }

    @Override
    public int createOrder(Order order){
        LString sql = "INSERT INTO `order`(memberId, price, payStatus) " +
                "VALUES (:memberId, :price, :payStatus)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", order.memberId);
        map.put("price", order.price);
        map.put("payStatus", order.payStatus);


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;

    }

}
