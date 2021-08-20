package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JYuan
 * @create 2021-08-20 17:05
 */
@Api(tags = "客户管理")
@RestController
public class CustomerController {
    @ApiOperation(value = "获取客户列表")
    @PostMapping("/findCustomer")
    public String findCustomer() {
        return null;
    }
}
