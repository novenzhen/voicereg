package com.choice.scm.webreg.rest;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : noven.zhen
 * @date : 2018-11-21
 * @email: zjm@choicesoft.com.cn
 */
@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static  Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static Result failure(int code,String message, Object data) {
        Result result = new Result(code,message);
        result.setData(data);
        return result;
    }
    public static Result failure(int code,String message) {
        Result result = new Result(code,message);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}