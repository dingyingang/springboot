package com.example.demo.web.bill;

import com.example.demo.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Login", description = "账单相关接口")
@RestController
@RequestMapping(value = "bill", produces = "application/json;charset=utf-8")
public class DemoController extends BaseController {

    @ApiOperation("详情")
    @GetMapping("getInfo")
    public Object getInfo() {

        return "hello word";
    }
}
