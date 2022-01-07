package com.example.store.service.impl;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.mapper.CartMapper;
import com.example.store.mapper.ProductMapper;
import com.example.store.service.ICartService;
import com.example.store.service.ex.DeleteException;
import com.example.store.service.ex.InsertDataException;
import com.example.store.service.ex.NoSuchProductException;
import com.example.store.service.ex.UpdateException;
import com.example.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/3.
 *
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void insertToCart(Cart cart, String username) {
        //首先判断该商品是否已经添加到购物车
        Cart cartInfo = cartMapper.findByUIdAndPid(cart.getUid(), cart.getPid());
        if(cartInfo==null){    //商品还没有添加到购物车，执行添加操作
            Integer row = cartMapper.insertToCart(cart,username,new Date());
            if(row!=1){
                throw new InsertDataException("商品添加到购物车失败！请重新添加");
            }
        }else{
            //商品已经添加到购物车，执行更新操作，只改变num
            Integer num = cartMapper.updateProductNum(cartInfo.getCid(), cart.getnum() + cartInfo.getnum(),username,new Date());
            if (num!=1){
                throw new UpdateException("商品添加到购物车失败！请重新添加");
            }
        }

    }

    //查询用户下的所有购物车商品
    @Override
    public List<CartVO> queryCartInfosByUid(Integer uid) {
        List<CartVO> carts = cartMapper.queryCartInfosByUid(uid);
        return carts;
    }

    //添加购物车商品数量
    @Override
    public Integer addNum(Integer cid, String username) {
        //首先判断该条购物车数据是否还存在
        Cart cart = cartMapper.queryCartByCid(cid);
        if(cart==null){
            throw new NoSuchProductException("该商品不存在，添加失败！");
        }
        Integer row = cartMapper.updateProductNum(cid, cart.getnum() + 1, username, new Date());
        if(row!=1){
            throw new UpdateException("数量添加失败！请重新添加");
        }
        return cart.getnum()+1;
    }

    @Override
    public Integer reduceNum(Integer cid, String username) {
        //首先判断该条购物车数据是否还存在
        Cart cart = cartMapper.queryCartByCid(cid);
        if (cart == null) {
            throw new NoSuchProductException("该商品不存在，减少失败！");
        }
        Integer row = cartMapper.updateProductNum(cid, cart.getnum() - 1, username, new Date());
        if (row != 1) {
            throw new UpdateException("数量减少失败！请重新操作");
        }
        return cart.getnum() - 1;
    }

    //通过cid删除一条购物车数据
    @Override
    public void deleteCart(Integer cid) {
        Integer row = cartMapper.deleteCartInfoByCid(cid);
        if(row!=1){
            throw new DeleteException("删除数据时发生异常，请重新操作！");
        }
    }

    @Override
    public List<CartVO> orderConfirmCheck(Integer[] cids,Integer uid) {
        List<CartVO> voList = cartMapper.queryAllByCid(cids);
        Iterator<CartVO> iterator = voList.iterator();
        while(iterator.hasNext()){
            CartVO next = iterator.next();
            if(next.getUid()!=uid){
                iterator.remove();
            }
        }
        return voList;
    }

    //删除指定的购物车数据
    @Override
    public void deleteCartByCids(Integer[] cids) {
        Integer row = cartMapper.deleteCartInfoByCids(cids);
        if(row!=cids.length){
            throw new DeleteException("删除数据时出现错误！");
        }
    }
}
