package com.example.controller;

import com.example.bean.Menu;
import com.example.bean.Result;
import com.example.bean.UserInfo;
import com.example.security.HasTokenAuthentication;
import com.example.service.UserService;
import com.example.util.CookieUtil;
import com.example.util.JWTUtil;
import com.example.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author JYuan
 * @create 2021-08-18 20:31
 */
@Api(tags = "用户管理")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", required = true, dataType = "String",defaultValue = "张三"), @ApiImplicitParam(name = "password", required = true, dataType = "String",defaultValue = "123456")})
    public Result<String> login(String username, String password) {
        return null;
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户退出")
    public Result<String> logout(@ApiIgnore HttpServletRequest request, @ApiIgnore HttpServletResponse response) {
        cookieUtil.remove(request,response);
        HasTokenAuthentication hasTokenAuthentication = (HasTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        redisUtil.del(hasTokenAuthentication.getKey());
        return Result.logout();
    }

    @PostMapping("/user/menus")
    @ApiOperation(value = "用户菜单")
    public Result<List<Menu>> menus(@ApiIgnore Authentication authentication) throws TimeoutException {
        HasTokenAuthentication hasTokenAuthentication = (HasTokenAuthentication) authentication;
        final String token = hasTokenAuthentication.getToken();
        if (token != null) {
            String username = JWTUtil.verify(token);
            final UserDetails userDetails = userService.loadUserByUsername(username);
            UserInfo userInfo = (UserInfo) userDetails;
            return new Result<>(200, null, userService.findMenus(userInfo.getId()));
        }
        throw new TimeoutException("身份过期或无效");
    }
}
