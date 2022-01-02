package com.example.store;

import com.example.store.entity.User;
import com.example.store.mapper.AddressMapper;
import com.example.store.mapper.DistrictMapper;
import com.example.store.mapper.ProductMapper;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    DistrictMapper districtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ProductMapper productMapper;
    @Test
    void contextLoads() throws Exception{
        System.out.println(productMapper.getDetailById(10000001));

    }

}
