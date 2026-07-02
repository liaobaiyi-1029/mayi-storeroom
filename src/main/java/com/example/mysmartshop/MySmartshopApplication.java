package com.example.mysmartshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.example.mysmartshop.mapper")
@EnableCaching
public class MySmartshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySmartshopApplication.class, args);
        System.out.println("\n" +
                "========================================\n" +
                "  智慧商品信息管理系统 启动成功!\n" +
                "  访问地址: http://localhost:8080\n" +
                "========================================\n");
    }

}

/**
 * 智慧商品信息管理系统 - 启动类
 *
 * @SpringBootApplication : Spring Boot 自动装配入口
 * @MapperScan            : 扫描 mapper 接口所在包，省去每个 Mapper 上写 @Mapper
 * @EnableCaching         : 开启声明式缓存，使 @Cacheable / @CachePut / @CacheEvict 生效
 *                          （阶段五：高并发场景下的双重缓存优化）
 */
