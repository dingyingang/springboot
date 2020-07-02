package com.example.demo.service.Impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getUserInfo(Integer userId) {
        return userMapper.getUserInfo(userId);
    }
}
