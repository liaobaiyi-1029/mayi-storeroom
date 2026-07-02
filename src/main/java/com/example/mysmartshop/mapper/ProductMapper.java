package com.example.mysmartshop.mapper;

import com.example.mysmartshop.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectByCondition(@Param("categoryId") Integer categoryId,
                                    @Param("name") String name,
                                    @Param("minPrice") BigDecimal minPrice,
                                    @Param("maxPrice") BigDecimal maxPrice);

    Product selectById(Integer id);

    int insert(Product product);

    int update(Product product);

    int delete(Integer id);
}