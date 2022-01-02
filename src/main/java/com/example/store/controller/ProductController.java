package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.service.IProductService;
import com.example.store.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/30.
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @RequestMapping("host_product")
    public JsonUtil<List> findHostProduct(){
        List<Product> hostProduct = productService.findHostProduct();
        return new JsonUtil<>(OK,hostProduct);
    }

    //查询商品明细
    @RequestMapping("{id}/detail")
    public JsonUtil<Product> getProductDetail(@PathVariable("id") Integer id){
        Product detail = productService.getProductDetailById(id);
        return new JsonUtil<>(OK,detail);
    }
}
