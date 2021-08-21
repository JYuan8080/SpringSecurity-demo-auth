package com.example.filter;

import com.example.bean.UserInfo;
import com.example.security.HasTokenAuthentication;
import com.example.service.UserService;
import com.example.util.CookieUtil;
import com.example.util.JWTUtil;
import com.example.util.RedisUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JYuan
 * @create 2021-08-19 18:28
 */
public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        /*
            由于Filter和Servlet无法使用自动注入属性，因此想要获取IOC容器中的组件，需要用以下方式进行
         */
        ServletContext context = httpServletRequest.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        UserDetailsService userDetailsService = ctx.getBean(UserDetailsService.class);
        RedisUtil redisUtil = ctx.getBean(RedisUtil.class);
        final CookieUtil cookieUtil = ctx.getBean(CookieUtil.class);

        cookieUtil.get(httpServletRequest,token -> {
            String username;
            try {
                username = JWTUtil.verify(token);
            } catch (Exception e) {
                httpServletRequest.setAttribute("errorMessage","身份无效");
                throw new BadCredentialsException("");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UserInfo userInfo = (UserInfo) userDetails;
            final Integer id = userInfo.getId();
            final Object value = redisUtil.get("user:id:" + id + ":id");
            if (value != null) {
                userInfo.setUserService((UserService) userDetailsService);
                HasTokenAuthentication hasTokenAuthentication = new HasTokenAuthentication(userInfo.getUsername(), userInfo.getPassword(), userInfo.getAuthorities());
                hasTokenAuthentication.setToken(token);
                hasTokenAuthentication.setKey("user:id:" + id + ":id");
                SecurityContextHolder.getContext().setAuthentication(hasTokenAuthentication);
            }else {
                httpServletRequest.setAttribute("errorMessage","身份已过期");
                throw new BadCredentialsException("");
            }
        });

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
