package com.example.mysmartshop;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class mima {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
