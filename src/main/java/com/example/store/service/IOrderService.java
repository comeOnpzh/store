package com.example.store.service;

import com.example.store.entity.Order;
import com.example.store.vo.OrderVO;

import java.util.List;
import java.util.Map;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public interface IOrderService {
    Order createOrder(Integer[] cids, Integer aid, Integer uid, String username);
    Order getOrderByOid(Integer oid);
    Order updatePayStatus(Integer oid,String username);
    Map<Integer, List> getOrderItemsByOid(Integer uid);

}
