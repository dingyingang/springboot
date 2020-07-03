package com.example.demo.web.user;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.config.annotation.PassLogger;
import com.example.demo.config.listener.ExcelListener;
import com.example.demo.dto.UserDto.request.UserDto;
import com.example.demo.entity.ResponseVo;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utils.ExcelUtil;
import com.example.demo.utils.ResultUtil;
import com.example.demo.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @PassLogger
    @ApiOperation("导入用户")
    @PostMapping("/importUser")
    public ResponseVo<UserEntity> importUser(@ApiParam("导入") @RequestParam("导入文件") MultipartFile file) throws IOException {


        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //传入参数
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
        //读取信息
        excelReader.read(new Sheet(1, 1, UserDto.class));
        //获取数据
        List<Object> list = listener.getDatas();


        return ResultUtil.success(list);
    }

    /**
     * 下载模板
     */
    @ApiOperation(value="下载Excel模板",notes = "下载Excel模板")
    @PostMapping(value = "/downloadExcel")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<UserDto> list = new ArrayList<UserDto>();
            UserDto userDto = new UserDto();
            userDto.setPhone("13799898978");
            userDto.setUserName("小岗");
            list.add(userDto);

            ExcelUtil.writeExcel(response, list, "信息", "Sheet1", new UserDto());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
