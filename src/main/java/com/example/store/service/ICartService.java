package com.example.store.service;

import com.example.store.entity.Cart;
import com.example.store.vo.CartVO;

import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/3.
 */
public interface ICartService {
    void insertToCart(Cart cart, String username);
    List<CartVO> queryCartInfosByUid(Integer uid);
    Integer addNum(Integer cid,String username);
    Integer reduceNum(Integer cid,String username);
    void deleteCart(Integer cid);
    List<CartVO> orderConfirmCheck(Integer[] cid,Integer uid);
    void deleteCartByCids(Integer[] cids);
}
