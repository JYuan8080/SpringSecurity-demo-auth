package com.example.config;

import com.example.filter.JWTFilter;
import com.example.security.*;
import com.example.security.LoginSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author JYuan
 * @create 2021-08-18 20:38
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Login login;
    @Autowired
    private LoginSuccess loginSuccess;
    @Autowired
    private LoginFail loginFail;
    @Autowired
    private UnAuth unAuth;
    @Autowired
    private UnLogin unLogin;

    @Bean

    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(login);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/swagger-ui/**", "/**/swagger-resources/**", "/**/v3/**");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() //cors()解决跨域问题，csrf()会与restful风格冲突，默认springsecurity是开启的，所以要disable()关闭一下
                // 不使用session，而使用token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                // 指定前端携带用户名的参数名
                .usernameParameter("username")
                // 指定前端携带密码的参数名
                .passwordParameter("password")
                // 登录成功逻辑处理
                .successHandler(loginSuccess)
                // 登录失败逻辑处理
                .failureHandler(loginFail)
                .and()
                .exceptionHandling()
                //权限不足的时候的逻辑处理
                .accessDeniedHandler(unAuth)
                //未登录时的逻辑处理
                .authenticationEntryPoint(unLogin)
                .and()
                .logout()
                .disable()
                // 自定义的过滤器，需要添加到security的过滤器链中，否则无法生效
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
