package com.example.store.controller;

import com.example.store.entity.Order;
import com.example.store.service.IAddressService;
import com.example.store.service.ICartService;
import com.example.store.service.IOrderItemService;
import com.example.store.service.IOrderService;
import com.example.store.util.JsonUtil;
import com.example.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 * 订单控制类，接受来自订单的请求
 */
@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

   @Autowired
   private IOrderService orderService;
   @Autowired
   private IOrderItemService orderItemService;
    /**
     * 创建订单
     * @param cids       需要生成订单的购物车信息
     * @param aid       创建订单需要的收货地址
     * @return
     */
    @RequestMapping("create")
    public JsonUtil<Order> createOrder(Integer[] cids, Integer aid, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUserNameFromSession(session);
        Order order = orderService.createOrder(cids, aid, uid, username);
        orderItemService.createOrderItem(order,cids,username);
        return new JsonUtil<>(OK,order);
    }

    @RequestMapping("find_by_oid")
    public JsonUtil<Order> findByOid(Integer oid){
        Order order = orderService.getOrderByOid(oid);
        return new JsonUtil<>(OK,order);
    }
    @RequestMapping("pay")
    public JsonUtil<Order> orderPay(Integer oid,HttpSession session){
        Order order = orderService.updatePayStatus(oid, getUserNameFromSession(session));
        return new JsonUtil<>(OK,order);
    }
    //查询用户名下所有订单信息
    @RequestMapping("all_orders")
    public JsonUtil<Map<Integer,List>> findOrdersByUid(HttpSession session){
        Map<Integer, List>  map = orderService.getOrderItemsByOid(getUidFromSession(session));
        return new JsonUtil<Map<Integer, List>>(OK,map);
    }
    //查询一个订单的详细信息
    @RequestMapping("one")
    public JsonUtil<Order> findOneOrderByOid(Integer oid){
        Order order = orderService.getOrderByOid(oid);
        return new JsonUtil<>(OK,order);
    }
    @RequestMapping("item")
    public JsonUtil<List> getOrderItemByOid(Integer oid){
        List<OrderVO> oneOrder = orderItemService.getOneOrder(oid);
        return new JsonUtil<>(OK,oneOrder);
    }
}
