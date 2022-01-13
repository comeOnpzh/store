package com.example.store.mapper;

import com.example.store.entity.Order;
import com.example.store.vo.OrderVO;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public interface OrderMapper {
    Integer createOrder(Order order);
    Order getOrderByOid(Integer oid);
    Integer updateOrderStatus(Order order);
    List<Integer> getOid(Integer uid);
}
