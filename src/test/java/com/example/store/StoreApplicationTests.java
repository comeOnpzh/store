package com.example.store;

import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Test
    void contextLoads() throws Exception{
        System.out.println(dataSource.getConnection());
        User user = new User();
        user.setUsername("hello");
        user.setPassword("123456");
        user.setAvatar("jfaksjfkajs");
        userService.register(user);
    }

}
