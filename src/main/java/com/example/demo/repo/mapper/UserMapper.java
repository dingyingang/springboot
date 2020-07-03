package com.example.demo.repo.mapper;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserEntity getUserInfo(@Param("userid") Integer userid);

    List<UserEntity> getUserList();
}
