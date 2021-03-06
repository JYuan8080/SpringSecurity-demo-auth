package com.example.security;

import com.alibaba.fastjson.JSON;
import com.example.bean.Result;
import com.example.util.CookieUtil;
import com.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 登录成功时返回给前端的数据
 * @author JYuan
 * @create 2021-08-19 19:28
 */
@Component
public class LoginSuccess implements AuthenticationSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Value("${token.time}")
    private Long time;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HasTokenAuthentication hasTokenAuthentication = (HasTokenAuthentication) authentication;
        cookieUtil.set(hasTokenAuthentication.getToken(),response);
        redisUtil.setex(hasTokenAuthentication.getKey(), time,hasTokenAuthentication.getToken());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.loginSuccess()));
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
