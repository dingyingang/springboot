package com.example.demo.service;


import com.example.demo.entity.UserEntity;
import com.github.pagehelper.PageInfo;

public interface UserService {

    UserEntity getUserInfo(Integer userId);

    PageInfo<UserEntity> getUserList(int page);
}
