package com.example.esun.dao.impl;

import com.example.esun.dao.ProductDao;
import com.example.esun.dto.ProductQueryParams;
import com.example.esun.dto.ProductRequest;
import com.example.esun.model.Product;
import com.example.esun.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public  createOrder(String orderId,Integer userId, Integer totalAmount,Integer payStatus) {
        String sql = "INSERT INTO `order`(orderId,memberId, total_amount,payStatus) " +
                "VALUES (:orderId,:memberId, :totalAmount, :payStatus)";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("memberId", memberId);
        map.put("totalAmount", totalAmount);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return ;
    }


}