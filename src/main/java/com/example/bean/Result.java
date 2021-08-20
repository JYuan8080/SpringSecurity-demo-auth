package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JYuan
 * @create 2021-08-19 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -577160551718336314L;
    private Integer code;
    private String message;
    private T data;

    public static Result<String> loginSuccess() {
        return new Result<String>(200, null, "登录成功");
    }

    public static Result<String> loginFail(String message) {
        return new Result<>(500, message, null);
    }

    public static Result<String> unLogin() {
        return new Result<>(401, "未登录", null);
    }

    public static Result<String> unAuth() {
        return new Result<>(403, "权限不足", null);
    }

    public static Result<String> logout() {
        return new Result<>(200, null, "退出成功");
    }
}
