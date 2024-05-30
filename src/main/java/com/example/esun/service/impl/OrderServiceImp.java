package com.example.esun.service.impl;


import com.example.esun.dto.CreateOrderRequest;
import com.example.esun.model.OrderDetail;
import com.example.esun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class OrderServiceImp implements OrderService {

    @Transactional
    @Override
    public String createOrder(String memberId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        String orderId="";
        List<orderDetail> orderDetailList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // 檢查庫存是否足夠
            if (product.getQuantity() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}",
                        buyItem.getProductId(), product.getQuantity(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品庫存
            productDao.updateQuantity(product.getProductId(), product.getQuantity() - buyItem.getQuantity());


            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            orderId = buyItem.getOrderId();

            // 轉換 BuyItem to OrderItem
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(buyItem.getProductId());
            orderDetail.setQuantity(buyItem.getQuantity());
            orderDetail.setItemPrice(amount);

            orderDetailList.add(orderDetail);
        }

        // 創建訂單
        orderDao.createOrder(orderId,memberId, totalAmount ,payStatus);
        orderDao.createOrderDetail(orderId, orderDetailList);

        return orderId;
    }


    @Override
    public void createOrderDetail(String orderId, List<OrderDetail> orderDetailList) {
        String sql = "INSERT INTO orderDetail(orderId, productId, quantity,standPrice, itemPrice) " +
                "VALUES (:orderId, :productId, :quantity, :standPrice, :itemPrice)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("standPrice", orderItem.getStandPrice());
            parameterSources[i].addValue("itemPrice", orderItem.getItemPrice());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    
    }

    @Override
    public List<OrderDetail> getMemberOrder(String memberId) {

        String sql = "SELECT od.orderId,od.productId,od.quantity,od.standPrice,od.itemPrice " +
                "FROM orderDetail as od " +
                "LEFT JOIN [order] as o ON od.orderId = o.orderId " +
                "WHERE o.memberId = :memberId";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);

        List<OrderDetail> orderDetailList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderDetailList;
    }

}
