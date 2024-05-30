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
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Override
    public List<Product> getProducts() {
        String sql = "SELECT productId, productName, price, quantity, " +
                "FROM product";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map);

        //庫存大於0
         sql = sql +"WHERE quantity > 0 ";

        // 排序
        sql = sql + " ORDER BY productId";

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT productId, productName, price, quantity " +
                "FROM product WHERE productId = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void updateQuantity(Integer productId, Integer quantity) {
        String sql = "UPDATE product SET quantity = :quantity" +
                " WHERE productId = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("quantity", quantity);

        namedParameterJdbcTemplate.update(sql, map);
    }
}