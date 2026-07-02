package com.example.mysmartshop.entity;


import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体 - 对应 product 表
 */
@Data
public class Product implements Serializable {
    private Integer    id;
    private String     name;          // 商品名称
    private Integer    categoryId;    // 所属分类 ID
    private String     categoryName;  // 分类名称（联表查询时填充）
    private BigDecimal price;         // 单价
    private Integer    stock;         // 库存
    private String     imageUrl;      // 商品图片 URL
    private String     description;   // 商品描述
    private Integer    status;        // 1 上架 / 0 下架
}
