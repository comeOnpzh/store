package com.example.store.service.impl;

import com.example.store.entity.Address;
import com.example.store.entity.Order;
import com.example.store.entity.OrderItem;
import com.example.store.mapper.AddressMapper;
import com.example.store.mapper.CartMapper;
import com.example.store.mapper.OrderItemMapper;
import com.example.store.mapper.OrderMapper;
import com.example.store.service.IOrderService;
import com.example.store.service.ex.InsertDataException;
import com.example.store.service.ex.NOSuchOrderException;
import com.example.store.service.ex.PayMentException;
import com.example.store.vo.CartVO;
import com.example.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 创建订单
     * @param cids   购物车id数组
     * @param aid      收货地址
     * @param uid        创建人
     */
    @Override
    public Order createOrder(Integer[] cids,Integer aid,Integer uid,String username) {
        long totalPrice =0l;
        List<CartVO> cartVOS = cartMapper.queryAllByCid(cids);
        for (CartVO cartVO : cartVOS) {
            //计算总价
            totalPrice+=(cartVO.getRealPrice()*cartVO.getNum());
        }
        //组织orderItem表信息
        //通过aid获得收货地址
        Address address = addressMapper.findByAid(aid);
        Order order = new Order();
        order.setTotalPrice(totalPrice);
        order.setRecvAddress(address.getAddress());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvName(address.getname());
        order.setUid(uid);
        Date date = new Date();
        order.setCreatedTime(date);
        order.setCreatedUser(username);
        order.setOrderTime(date);
        order.setStatus(0);
        order.setModifiedTime(date);
        order.setModifiedUser(username);
        Integer row = orderMapper.createOrder(order);
        if(row!=1){
            throw new InsertDataException("订单创建异常！");
        }
        //组织order表完毕
        return order;       //返回改订单在orderItem表中使用
    }

    @Override
    public Order getOrderByOid(Integer oid) {
        Order order = orderMapper.getOrderByOid(oid);
        if(order==null){
            throw new NOSuchOrderException("订单信息不存在,无法支付！");
        }
        return order;
    }

    @Override
    public Order updatePayStatus(Integer oid,String username) {
        Order order = orderMapper.getOrderByOid(oid);
        if(order==null){
            throw new NOSuchOrderException("订单不存在！");
        }
        //此处应调用支付接口,返回true则支付成功，返回false则支付失败
        if(true){
            Date nowdate = new Date();
            order.setStatus(1);
            order.setModifiedUser(username);
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setModifiedTime(Timestamp.valueOf(simpleDate.format(nowdate)));
            order.setPayTime(Timestamp.valueOf(simpleDate.format(nowdate)));
        }
        orderMapper.updateOrderStatus(order);
        return order;
    }

    @Override
    public Map<Integer, List> getOrderItemsByOid(Integer uid) {
        //先获取用户名下的oid
        List<Integer> oids = orderMapper.getOid(uid);
        Map<Integer, List> result = new HashMap<>();
        //遍历得到的oid，获取订单详细数据
        for (Integer oid : oids) {
            List<OrderVO> item = orderItemMapper.getItemByOid(oid);
            if (item != null && item.size() != 0) {
                //将订单详细放进结果集中
                result.put(oid, item);
            }
        }
        return result;
    }

}
