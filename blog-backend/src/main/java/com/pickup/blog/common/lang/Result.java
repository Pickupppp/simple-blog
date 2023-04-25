package com.pickup.blog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        return success("SUCCESS", data);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(400);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        return error(msg, null);
    }
}
