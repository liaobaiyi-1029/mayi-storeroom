package com.example.mysmartshop.service.impl;


import com.example.mysmartshop.entity.Category;
import com.example.mysmartshop.mapper.CategoryMapper;
import com.example.mysmartshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_KEY = "category:list";

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        List<Category> cached = (List<Category>) redisTemplate.opsForValue().get(CACHE_KEY);
        if (cached != null && !cached.isEmpty()) {
            System.out.println("命中 Redis 缓存（分类列表）");
            return cached;
        }
        List<Category> list = categoryMapper.selectAll();
        if (list != null && !list.isEmpty()) {
            redisTemplate.opsForValue().set(CACHE_KEY, list, 1, TimeUnit.HOURS);
            System.out.println("? 从 MySQL 读取分类列表并写入 Redis");
        }
        return list;
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    @Transactional
    public void insert(Category category) {
        categoryMapper.insert(category);
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    @Transactional
    public void update(Category category) {
        categoryMapper.update(category);
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoryMapper.delete(id);
        redisTemplate.delete(CACHE_KEY);
    }
}