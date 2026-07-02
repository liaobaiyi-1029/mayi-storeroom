package com.example.mysmartshop.service;


import com.example.mysmartshop.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    void insert(Category category);
    void update(Category category);
    void delete(Integer id);
}
