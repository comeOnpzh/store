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
        String a = "afajfasj";
        String substring = a.substring(2,4);
        System.out.println(substring);
    }

}
