package com.example;

import com.example.mapper.UserMapper;
import com.example.util.RandomSalt;
import com.example.util.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void getPassword() {
        Assertions.assertTrue(passwordEncoder.matches("123456","$2a$10$GoqG6SoFaMSNvsDmc1D7fefzAeMdoem81/LegghfsMScm/zSGr742"));
    }

    @Test
    void getSalt() {
        System.out.println(RandomSalt.getSalt(8));
    }

    @Test
    void testRedis() {
        Assertions.assertTrue(redisUtil.set("testRedis", "hello world"));
    }

}
