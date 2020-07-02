package com.example.demo.entity;

import com.example.demo.enums.ExceptionMsg;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2018 Shanghai Yejia Digital Technology Co., Ltd. All rights reserved.
 *
 * @author yy
 * @date 2019/6/2
 */
public class ResponseVo<T> {

    @ApiModelProperty(value = "返回状态码")
    private Integer code = 200;

    @ApiModelProperty(value = "提示信息列表")
    private List<String> msg = new ArrayList<>();

    @ApiModelProperty(value = "返回结果")
    private T data;

    public ResponseVo() {
        this.setCode(ExceptionMsg.SUCCESS.getCode());
        List<String> msgList = new ArrayList<>();
        msgList.add(ExceptionMsg.SUCCESS.getMsg());
        this.setMsg(msgList);
    }

    public ResponseVo(T data) {
        this.setCode(ExceptionMsg.SUCCESS.getCode());
        List<String> msgList = new ArrayList<>();
        msgList.add(ExceptionMsg.SUCCESS.getMsg());
        this.setData(data);
    }

    public ResponseVo(ExceptionMsg msg) {
        this.setCode(msg.getCode());
        List<String> msgList = new ArrayList<>();
        msgList.add(msg.getMsg());
        this.setMsg(msgList);
    }

    public ResponseVo(ExceptionMsg msg, String rspMsg, T data) {
        this.setCode(msg.getCode());
        List<String> msgList = new ArrayList<>();
        msgList.add(rspMsg);
        this.setMsg(msgList);
        this.setData(data);
    }

    public ResponseVo(ExceptionMsg msg, List<String> rspMsg, T data) {
        this.setCode(msg.getCode());
        this.setMsg(rspMsg);
        this.setData(data);
    }

    public ResponseVo(ExceptionMsg msg, T data) {
        this.setCode(msg.getCode());
        List<String> msgList = new ArrayList<>();
        msgList.add(msg.getMsg());
        this.setMsg(msgList);
        this.setData(data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HrsResponse{" +
                "code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                '}';
    }
}
