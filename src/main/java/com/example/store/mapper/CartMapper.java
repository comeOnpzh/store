package com.example.store.mapper;

import com.example.store.entity.Cart;
import com.example.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/2.
 * 购物车接口类
 */
public interface CartMapper {
    Cart findByUIdAndPid(Integer uid,Integer pid);      //通过用户id和商品id查询记录
    Integer updateProductNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);       //更新购物车数量
    Integer insertToCart(Cart cart,String modifiedUser, Date modifiedTime);       //插入
    List<CartVO> queryCartInfosByUid(Integer uid);      //查询用户下的购物车信息
    Cart queryCartByCid(Integer cid);
    Integer deleteCartInfoByCid(Integer cid);
    Integer deleteCartInfoByCids(@Param("cids") Integer[] cids);
    List<CartVO> queryAllByCid(@Param("cids") Integer cids[]);

}
