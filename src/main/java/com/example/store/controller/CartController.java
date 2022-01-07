package com.example.store.controller;

import com.example.store.entity.Cart;
import com.example.store.service.ICartService;
import com.example.store.util.JsonUtil;
import com.example.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/3.
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;


    @RequestMapping("add_to_cart")
    public JsonUtil<Void> insertToCart(Cart cart, HttpSession session){
        Integer uid = getUidFromSession(session);
        cart.setUid(uid);
        cart.setCreatedUser(getUserNameFromSession(session));
        cart.setCreatedTime(new Date());
        cartService.insertToCart(cart,getUserNameFromSession(session));
        return new JsonUtil<>(OK);
    }


    //查询用户购物车的所有商品
    @RequestMapping("get_all_product")
    public JsonUtil<List> getAllProduct(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<CartVO> carts = cartService.queryCartInfosByUid(uid);
        return new JsonUtil<List>(OK,carts);
    }

    //添加购物车商品数量
    @RequestMapping("{cid}/add_num")
    public JsonUtil<Integer> addNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer num = cartService.addNum(cid, getUserNameFromSession(session));
        return new JsonUtil<>(OK,num);
    }
    //减少购物车商品数量
    @RequestMapping("{cid}/reduce_num")
    public JsonUtil<Integer> reduceNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer num = cartService.reduceNum(cid, getUserNameFromSession(session));
        return new JsonUtil<>(OK,num);
    }
    //通过cid删除一条购物车数据
    @RequestMapping("{cid}/delete_cart")
    public JsonUtil<Void> deleteCartInfo(@PathVariable("cid") Integer cid){
        cartService.deleteCart(cid);
        return new JsonUtil<>(OK);
    }

    @RequestMapping("list")
    public JsonUtil<List> querycheckOrderBycids(Integer[] cids,HttpSession session){
        List<CartVO> list = cartService.orderConfirmCheck(cids, getUidFromSession(session));
        return new JsonUtil<>(OK,list);
    }

    @RequestMapping("delete")
    public JsonUtil<Void> deleteCartInfo(@RequestParam("cids") Integer[] cids){
        cartService.deleteCartByCids(cids);
        return new JsonUtil<>(OK);
    }
    @RequestMapping("delete_one")
    public JsonUtil<Void> deleteOne(Integer cid){
        cartService.deleteCart(cid);
        return new JsonUtil<>(OK);
    }
}
