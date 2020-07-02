package com.example.demo.utils;


import com.example.demo.entity.ResponseVo;
import com.example.demo.enums.ExceptionMsg;

import java.util.List;

/**
 * 返回信息封装
 * Copyright © 2018 Shanghai Yejia Digital Technology Co., Ltd. All rights reserved.
 *
 * @author yy
 * @date 2019/4/03
 */
public class ResultUtil {
    public static ResponseVo success() {
        ResponseVo responseVo = new ResponseVo(ExceptionMsg.SUCCESS);
        return responseVo;
    }
    public static ResponseVo success(Object object, List<String> msg) {
        ResponseVo responseVo = new ResponseVo(ExceptionMsg.SUCCESS, msg, object);
        return responseVo;
    }

    public static ResponseVo success(Object object) {
        ResponseVo responseVo = new ResponseVo(object);
        return responseVo;
    }

    public static ResponseVo error(ExceptionMsg code, List<String> msg) {
        ResponseVo responseVo = new ResponseVo(code, msg, null);
        return responseVo;
    }

    public static ResponseVo error(ExceptionMsg code, String msg) {
        ResponseVo responseVo = new ResponseVo(code, msg, null);
        return responseVo;
    }

    public static ResponseVo error(ExceptionMsg code) {
        ResponseVo responseVo = new ResponseVo(code, null);
        return responseVo;
    }
}
