package com.example.mysmartshop.service;

public interface UserService {
    boolean existsByUsername(String username);
    void register(String username, String password, String realName, String email, String phone);
}