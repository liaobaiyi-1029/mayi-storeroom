package com.example.mysmartshop.service.impl;

import com.example.mysmartshop.entity.Role;
import com.example.mysmartshop.entity.User;
import com.example.mysmartshop.entity.UserRole;
import com.example.mysmartshop.mapper.RoleMapper;
import com.example.mysmartshop.mapper.UserMapper;
import com.example.mysmartshop.mapper.UserRoleMapper;
import com.example.mysmartshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.selectByUsername(username) != null;
    }

    @Override
    @Transactional
    public void register(String username, String rawPassword, String realName, String email, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRealName(realName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatus(1);
        userMapper.insert(user);

        Role normalRole = roleMapper.selectByRoleName("ROLE_user");
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        ur.setRoleId(normalRole.getId());
        userRoleMapper.insert(ur);
    }
}