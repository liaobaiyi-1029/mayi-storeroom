package com.example.mysmartshop.service.impl;

import com.example.mysmartshop.entity.Product;
import com.example.mysmartshop.mapper.ProductMapper;
import com.example.mysmartshop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String ALL_PRODUCTS_CACHE = "product:all";

    @Override
    public PageInfo<Product> findByCondition(Integer categoryId, String name, BigDecimal minPrice, BigDecimal maxPrice,
                                             int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.selectByCondition(categoryId, name, minPrice, maxPrice);
        return new PageInfo<>(list);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Product findById(Integer id) {
        System.out.println("🔍 执行SQL查询单商品（id=" + id + "）");
        return productMapper.selectById(id);
    }

    @Override
    @Transactional
    @CachePut(value = "product", key = "#result.id")
    public Product insert(Product product) {
        productMapper.insert(product);
        redisTemplate.delete(ALL_PRODUCTS_CACHE);
        return product;
    }

    @Override
    @Transactional
    @CachePut(value = "product", key = "#product.id")
    public Product update(Product product) {
        productMapper.update(product);
        redisTemplate.delete(ALL_PRODUCTS_CACHE);
        return product;
    }

    @Override
    @Transactional
    @CacheEvict(value = "product", key = "#id")
    public void delete(Integer id) {
        productMapper.delete(id);
        redisTemplate.delete(ALL_PRODUCTS_CACHE);
    }
}