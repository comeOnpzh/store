package com.example.store;

import com.example.store.entity.Order;
import com.example.store.entity.User;
import com.example.store.mapper.*;
import com.example.store.service.IOrderService;
import com.example.store.service.IUserService;
import com.example.store.vo.OrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

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
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IOrderService orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Test
    void contextLoads() throws Exception{
        Integer[] cids =new Integer[]{7,8};
        List<OrderVO> oid = orderItemMapper.getItemByOid(4);
        System.out.println(oid);

    }

}
