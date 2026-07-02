package com.example.mysmartshop.mapper;


import com.example.mysmartshop.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;


@Mapper
public interface RoleMapper {
    @Select("SELECT r.* FROM t_role r JOIN t_user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId}")
    List<Role> selectRolesByUserId(Integer userId);

    @Select("SELECT * FROM t_role WHERE name = #{roleName}")
    Role selectByRoleName(String roleName);
}