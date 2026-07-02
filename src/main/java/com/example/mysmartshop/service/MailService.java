package com.example.mysmartshop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;   // 关键：显式导入 java.util.concurrent.TimeUnit

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final StringRedisTemplate redisTemplate;
    private final String mailUsername;

    // 构造器注入（解决 field injection 警告）
    public MailService(JavaMailSender mailSender,
                       StringRedisTemplate redisTemplate,
                       @Value("${spring.mail.username}") String mailUsername) {
        this.mailSender = mailSender;
        this.redisTemplate = redisTemplate;
        this.mailUsername = mailUsername;
    }

    private static final String VERIFY_CODE_PREFIX = "verify:code:";
    private static final long EXPIRE_MINUTES = 5;

    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public boolean sendVerifyCode(String email) {
        try {
            String code = generateCode();
            String key = VERIFY_CODE_PREFIX + email;
            // 使用 TimeUnit.MINUTES
            redisTemplate.opsForValue().set(key, code, EXPIRE_MINUTES, TimeUnit.MINUTES);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailUsername);   // 必须设置发件人
            message.setTo(email);
            message.setSubject("智慧商品管理系统 - 注册验证码");
            message.setText("您的注册验证码为：" + code + "，有效期5分钟。");
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyCode(String email, String code) {
        String key = VERIFY_CODE_PREFIX + email;
        String savedCode = redisTemplate.opsForValue().get(key);
        if (savedCode == null) {
            return false;   // IDE 可能误报，但实际不会 always false
        }
        if (savedCode.equals(code)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}