package com.example.mysmartshop.mapper;

import com.example.mysmartshop.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM category ORDER BY id")
    List<Category> selectAll();

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Integer id);

    @Insert("INSERT INTO category(name, description) VALUES(#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Category category);

    @Update("UPDATE category SET name=#{name}, description=#{description} WHERE id=#{id}")
    int update(Category category);

    @Delete("DELETE FROM category WHERE id=#{id}")
    int delete(Integer id);
}