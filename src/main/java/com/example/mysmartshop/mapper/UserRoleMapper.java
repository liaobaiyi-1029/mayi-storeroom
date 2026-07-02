package com.example.mysmartshop.mapper;

import com.example.mysmartshop.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    @Insert("INSERT INTO t_user_role(user_id, role_id) VALUES(#{userId}, #{roleId})")
    int insert(UserRole userRole);
}
