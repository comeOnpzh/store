package com.example.store.service.impl;

import com.example.store.entity.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.service.IProductService;
import com.example.store.service.ex.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/30.
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findHostProduct() {
        return productMapper.findHostList();
    }

    @Override
    public Product getProductDetailById(Integer id) {
        Product product = productMapper.getDetailById(id);
        if(product==null){
            throw new NoSuchProductException();
        }
        return product;
    }
}
