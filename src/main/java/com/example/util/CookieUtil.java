package com.example.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

/**
 * @author JYuan
 * @create 2021-08-21 11:39
 */
@Component
@ConfigurationProperties(prefix = "token")
public class CookieUtil {
    private String name;
    private int time;

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void set(String token, HttpServletResponse response) {
        final Cookie cookie = new Cookie(name,token);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void get(HttpServletRequest request,Consumer<String> consumer) {
        String token = null;
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
            if (token != null) {
                consumer.accept(token);
            }
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
