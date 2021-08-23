package com.example.mapper;

import com.example.bean.Menu;
import com.example.bean.UserInfo;

import java.util.List;

/**
 * @author JYuan
 * @create 2021-08-19 11:05
 */
public interface UserMapper {
    /** 根据名称查找
     * @param name
     * @return
     */
    UserInfo findByName(String name);

    /** 根据username获取权限
     * @param name
     * @return
     */
    List<Menu> getAuthorities(String name);
}
