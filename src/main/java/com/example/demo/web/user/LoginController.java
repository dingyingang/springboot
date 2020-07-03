package com.example.demo.web.user;

import com.example.demo.config.annotation.PassLogger;
import com.example.demo.entity.ResponseVo;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResultUtil;
import com.example.demo.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Login", description = "登录相关接口")
@RestController
@RequestMapping(value = "login", produces = "application/json;charset=utf-8")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    //@ApiImplicitParam(name = "Authorization", access = "hidden")
    @ApiOperation("密码登录")
    @PostMapping
    public Object login() {

        return "hello word";
    }

    @PassLogger
    @ApiOperation("获取用户详情")
    @PostMapping("/getUserInfo")
    @ApiImplicitParam(name = "Authorization", access = "hidden")
    public ResponseVo<UserEntity> getUserInfo() {


        return ResultUtil.success(userService.getUserInfo(1));
    }

    @PassLogger
    @ApiOperation("获取用户列表")
    @GetMapping("/getUserList")
    public ResponseVo<UserEntity> getUserList(@ApiParam("page") @RequestParam("page") int page) {


        return ResultUtil.success(userService.getUserList(page));
    }

}
