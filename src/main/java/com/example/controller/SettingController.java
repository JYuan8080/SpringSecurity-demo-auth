package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JYuan
 * @create 2021-08-20 17:05
 */
@Api(tags = "设置")
@RestController
public class SettingController {
    @ApiOperation(value = "获取设置列表")
    @PostMapping("/findSetting")
    public String findSetting() {
        return null;
    }
}
