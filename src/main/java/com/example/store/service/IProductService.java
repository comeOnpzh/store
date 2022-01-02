package com.example.store.service;

import com.example.store.entity.Product;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/30.
 */
public interface IProductService {
    List<Product> findHostProduct();
    Product getProductDetailById(Integer id);
}
