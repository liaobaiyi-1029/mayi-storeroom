package com.example.mysmartshop.service;

import com.example.mysmartshop.entity.Product;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;

public interface ProductService {
    PageInfo<Product> findByCondition(Integer categoryId, String name, BigDecimal minPrice, BigDecimal maxPrice,
                                      int pageNum, int pageSize);
    Product findById(Integer id);
    Product insert(Product product);
    Product update(Product product);
    void delete(Integer id);
}