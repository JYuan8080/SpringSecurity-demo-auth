package com.example.security;

import com.alibaba.fastjson.JSON;
import com.example.bean.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JYuan
 * @create 2021-08-19 19:28
 */
@Component
public class LoginFail implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.loginFail(e.getMessage())));
    }
}
