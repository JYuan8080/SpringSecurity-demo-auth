package com.example.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author JYuan
 * @create 2021-08-19 19:39
 */
public class HasTokenAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = -6220464742038479088L;
    private String token;
    private String key;

    public HasTokenAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public HasTokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
