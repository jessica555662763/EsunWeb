package com.example.esun.repository;

import com.example.esun.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
}
