package com.example.mysmartshop.entity;


import lombok.Data;
import java.io.Serializable;

//用户实体 - 对应 t_user 表

@Data
public class User implements Serializable {
    private Integer id;
    private String  username;
    private String  password;     // BCrypt 加密后的密文
    private String  realName;
    private String  email;
    private String  phone;
    private Integer status;       // 1 启用 / 0 禁用
}
