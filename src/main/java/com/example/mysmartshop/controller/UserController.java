package com.example.mysmartshop.controller;



import com.example.mysmartshop.service.MailService;
import com.example.mysmartshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/sendCode")
    @ResponseBody
    public Map<String, Object> sendCode(@RequestParam String email) {
        Map<String, Object> result = new HashMap<>();
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            result.put("success", false);
            result.put("msg", "邮箱格式不正确");
            return result;
        }
        boolean ok = mailService.sendVerifyCode(email);
        if (ok) {
            result.put("success", true);
            result.put("msg", "验证码已发送至您的邮箱，请注意查收");
        } else {
            result.put("success", false);
            result.put("msg", "验证码发送失败，请稍后重试");
        }
        return result;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String email,
                           @RequestParam String verifyCode,
                           @RequestParam(required = false) String realName,
                           @RequestParam(required = false) String phone,
                           Model model) {
        // 校验验证码
        if (!mailService.verifyCode(email, verifyCode)) {
            model.addAttribute("error", "验证码错误或已过期");
            return "register";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次密码输入不一致");
            return "register";
        }

        if (userService.existsByUsername(username)) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }

        userService.register(username, password, realName, email, phone);
        return "redirect:/login?registered";
    }
}