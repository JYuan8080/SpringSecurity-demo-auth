package com.example.security;

import com.alibaba.fastjson.JSON;
import com.example.bean.Result;
import com.example.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JYuan
 * @create 2021-08-19 19:14
 */
@Component
public class UnLogin implements AuthenticationEntryPoint {
    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        cookieUtil.remove(request,response);
        final String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            response.getWriter().write(JSON.toJSONString(new Result<>(401, message, null)));
        } else {
            response.getWriter().write(JSON.toJSONString(Result.unLogin()));
        }
    }
}
