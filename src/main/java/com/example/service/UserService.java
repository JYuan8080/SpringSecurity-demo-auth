package com.example.service;

import com.example.bean.Menu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

/**
 * @author JYuan
 * @create 2021-08-18 21:45
 */
public interface UserService extends UserDetailsService {
    /** 获取用户具有的权限
     * @param id 用户id
     * @return 权限集合
     */
    Collection<? extends GrantedAuthority> getAuthorities(Integer id);

    /** 获取用户可以访问的菜单
     * @param id
     * @return
     */
    List<Menu> findMenus(Integer id);
}
