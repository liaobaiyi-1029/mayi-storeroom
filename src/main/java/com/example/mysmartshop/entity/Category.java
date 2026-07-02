package com.example.mysmartshop.entity;


import lombok.Data;
import java.io.Serializable;

//商品分类实体 - 对应 category 表

@Data
public class Category implements Serializable {
    private Integer id;
    private String  name;         // 分类名称，如 "数码电子"
    private String  description;  // 分类描述
}
