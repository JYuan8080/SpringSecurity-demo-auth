package com.example.bean;

import com.example.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author JYuan
 * @create 2021-08-19 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements UserDetails {
    private static final long serialVersionUID = -1592681499884240985L;
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String phone;
    private String createTime;
    private UserService userService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userService.getAuthorities(getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
