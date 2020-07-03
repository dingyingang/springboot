package com.example.demo.service.Impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getUserInfo(Integer userId) {
        return userMapper.getUserInfo(userId);
    }

    @Override
    public PageInfo<UserEntity> getUserList(int page) {

        PageHelper.startPage(page, 2);

        List<UserEntity> list = userMapper.getUserList();
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
        List<UserEntity> result = pageInfo.getList();
        return pageInfo;
    }
}
