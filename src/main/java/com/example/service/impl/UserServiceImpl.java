package com.example.service.impl;

import com.example.bean.Menu;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JYuan
 * @create 2021-08-18 21:48
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findByName(username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(Integer id) {
        List<Menu> list = userMapper.getAuthorities(id);
        return list.stream().map(item -> new SimpleGrantedAuthority(item.getCode())).collect(Collectors.toSet());
    }

    @Override
    public List<Menu> findMenus(Integer id) {
        List<Menu> menus = userMapper.getAuthorities(id);

        final ArrayList<Menu> list = new ArrayList<>();
        final ArrayList<Menu> subList = new ArrayList<>();
        final HashSet<Integer> set = new HashSet<>();
        // 选出所有pid
        menus.forEach(item -> set.add(item.getPid()));

        // 选出pid对应的menu
        set.forEach(pid -> {
            menus.forEach(item -> {
                if (item.getId().equals(pid)) {
                    list.add(item);
                }
            });
        });

        // menus中的menu根据pid进行填充
        list.forEach(item -> {
            menus.forEach(menusItem -> {
                if (menusItem.getPid().equals(item.getId())) {
                    subList.add(menusItem);
                }
            });

            item.setChildren(new ArrayList<>(subList));
            subList.clear();
        });

        // 选出菜单中pid = 0的menu
        list.clear();
        menus.forEach(item -> {
            if (item.getPid().equals(0)) {
                list.add(item);
            }
        });
        return list;
    }
}
