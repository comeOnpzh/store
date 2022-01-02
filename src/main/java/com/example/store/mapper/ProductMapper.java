package com.example.store.mapper;

import com.example.store.entity.Product;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/30.
 */
public interface ProductMapper {
    List<Product> findHostList();
    Product getDetailById(Integer id);
}
