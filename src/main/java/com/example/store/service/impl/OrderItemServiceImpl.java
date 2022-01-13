package com.example.store.service.impl;

import com.example.store.entity.Order;
import com.example.store.entity.OrderItem;
import com.example.store.mapper.CartMapper;
import com.example.store.mapper.OrderItemMapper;
import com.example.store.service.IOrderItemService;
import com.example.store.service.ex.InsertDataException;
import com.example.store.service.ex.NOSuchOrderException;
import com.example.store.vo.CartVO;
import com.example.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    //创建订单详细信息
    @Override
    public void createOrderItem(Order order, Integer[] cids, String username) {
        //获取到需要创建订单的cid值
        List<CartVO> cartVOS = cartMapper.queryAllByCid(cids);
        List<OrderItem> itemList = new ArrayList<>();
        for (CartVO cartVO : cartVOS) {
            //一个cid对应一个cartVO，每个VO都要创建一个商品详情订单记录
            OrderItem orderItem = new OrderItem();
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getPrice());
            orderItem.setOid(order.getOid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setNum(cartVO.getNum());
            orderItem.setPid(cartVO.getPid());
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            itemList.add(orderItem);
        }
        Integer rows = orderItemMapper.insertOrderItem(itemList);  //返回的行数是该次插入的总共插入行数
        if(rows!=cartVOS.size()){
            throw new InsertDataException("生成订单失败，请重新下订单！");
        }
    }


    //通过oid查询订单详细信息
    @Override
    public List<OrderVO> getOneOrder(Integer oid){
        List<OrderVO> item = orderItemMapper.getItemByOid(oid);
        if(item==null||item.size()==0){
            throw new NOSuchOrderException("订单信息不存在！");
        }
        return item;
    }
}
