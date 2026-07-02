package com.example.mysmartshop.entity;



import lombok.Data;
import java.io.Serializable;

//角色实体 - 对应 t_role表 系统内置两个角色：ROLE_admin（管理员）、ROLE_user（普通用户）

@Data
public class Role implements Serializable {
    private Integer id;
    private String  name;         // 角色标识，如 ROLE_admin
    private String  nameZh;       // 中文显示名，如 "管理员"
}
