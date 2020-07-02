package com.example.demo.entity;

import io.swagger.annotations.ApiModelProperty;

public class UserEntity {

    @ApiModelProperty("主键唯一标识")
    private Integer id;


    private Integer staffId;

    private Integer channelContactId;
    private Integer userTypeId;
    private String password;
    @ApiModelProperty("手机号")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getChannelContactId() {
        return channelContactId;
    }

    public void setChannelContactId(Integer channelContactId) {
        this.channelContactId = channelContactId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", staffId=" + staffId +
                ", channelContactId=" + channelContactId +
                ", userTypeId=" + userTypeId +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
