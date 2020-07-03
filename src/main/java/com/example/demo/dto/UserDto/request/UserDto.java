package com.example.demo.dto.UserDto.request;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class UserDto extends BaseRowModel {


    @ExcelProperty(value = "姓名",index = 0)
    private String userName;

    @ExcelProperty(value = "电话号",index = 1)
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
