package com.example.store.service;

import com.example.store.entity.Order;
import com.example.store.vo.OrderVO;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public interface IOrderItemService {
    void createOrderItem(Order order, Integer[] cids, String username);
    List<OrderVO> getOneOrder(Integer oid);
}
