package com.example.mysmartshop.mapper;

import com.example.mysmartshop.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    User selectByUsername(String username);

    @Insert("INSERT INTO t_user(username, password, real_name, email, phone, status) " +
            "VALUES(#{username}, #{password}, #{realName}, #{email}, #{phone}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
}