
package com.example.demo.enums;

/**
 * Copyright © 2018 Shanghai Yejia Digital Technology Co., Ltd. All rights reserved.
 *
 * @author yy
 * @date 2019/6/2
 */
public enum ExceptionMsg {

    SUCCESS(200, "操作成功"),
    ALREADYEXISTS(201, "已经存在"),
    FAILED(400, "操作失败"),
    ParamError(401, "参数错误！"),
    FriendTrip(4001,"友情提示"),
    SERVER_ERROR(500, "服务器错误"),


    REPEAT_SUBMIT_OPERATION_EXCEPTION(707,"重复提交"),

    /*权限登录相关*/
    LOGINOUT(1001, "登录信息已过期，请重新登陆！"),



    /**
     * 基础数据（组织）
     */
    BASIC_ORGANIZE_EMPTY(1501, "组织数据为空"),

    /**
     * 补缴相关
     */
    Insured_closse(405,"操作失败！"),
    Insured_make(406,"操作失败！"),

    ;
    private ExceptionMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ExceptionMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}

