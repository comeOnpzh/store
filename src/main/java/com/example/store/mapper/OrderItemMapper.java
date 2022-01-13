package com.example.store.mapper;

import com.example.store.entity.OrderItem;
import com.example.store.vo.OrderVO;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public interface OrderItemMapper {
    Integer insertOrderItem(List<OrderItem> list);

    List<OrderVO> getItemByOid(Integer uid);
}
