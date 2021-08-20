package com.example.security;

import com.example.bean.UserInfo;
import com.example.service.UserService;
import com.example.util.JWTUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author JYuan
 * @create 2021-08-18 21:36
 */
@Component
public class Login implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /*
            该方法中抛出AuthenticationException及其子类型的异常会被异常过滤器捕获到，并进行处理
         */
        //获取用户名
        String account = authentication.getName();
        //获取密码
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        if (userDetails != null) {
            boolean checkPassword = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
            if (!checkPassword) {
                throw new BadCredentialsException("账号或密码不正确!");
            }
            UserInfo userInfo = (UserInfo) userDetails;
            userInfo.setUserService((UserService) userDetailsService);
            HasTokenAuthentication authenticationToken = new HasTokenAuthentication(account, password, userInfo.getAuthorities());
            authenticationToken.setKey("user:id:" + userInfo.getId() + ":id");
            authenticationToken.setToken(JWTUtil.getJWT(userInfo.getUsername()));
            return authenticationToken;
        } else {
            throw new BadCredentialsException("账号或密码不正确!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
