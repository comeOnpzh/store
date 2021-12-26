package com.example.store.controller;

import com.example.store.entity.Address;
import com.example.store.mapper.AddressMapper;
import com.example.store.service.IAddressService;
import com.example.store.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 */
@RestController
@RequestMapping("address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("insert_address")
    public JsonUtil<Void> insertAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String name = getUserNameFromSession(session);
        addressService.insertAddress(uid, name, address);
        return new JsonUtil<>(OK);
    }

    //查询用户的所有收货地址
    @RequestMapping("all_address")
    public JsonUtil<List> findAddress(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> alladdress = addressService.findAlladdress(uid);
        return new JsonUtil<>(OK, alladdress);
    }


    //修改默认地址,这里需要使用RestFul风格的请求路径
    @RequestMapping("{aid}/change_default")
    public JsonUtil<Void> changeDefaultAddress(@PathVariable("aid") Integer aid,
                                               HttpSession session){
        addressService.changeDefaultAddress(getUidFromSession(session),aid,getUserNameFromSession(session));
        return new JsonUtil<>(OK);
    }

    //删除收货地址
    @RequestMapping("{aid}/delete_address")
    public JsonUtil<Void> deleteAddress(@PathVariable("aid") Integer aid){
        addressService.deleteAddress(aid);
        return new JsonUtil<>(OK);
    }

    //将需要修改的收货地址保存到session中
    @RequestMapping("{aid}/change_address")
    public JsonUtil<Void> changeAddress(@PathVariable("aid") Integer aid, HttpSession session){
        Address address = addressMapper.findByAid(aid);
        session.setAttribute("address",address);
        return new JsonUtil<>(OK);
    }

    //addaddress.html页面初始化完成之前，通过此方法判断是不是收货地址修改请求，如果是则返回需要修改的address对象并回显到页面上
    @RequestMapping("change_request")
    public JsonUtil<Address> changeRequest(HttpSession session){
        Address address = (Address) session.getAttribute("address");
        if(address!=null){
            session.removeAttribute("address");
            return new JsonUtil<>(OK,address);
        }
        return new JsonUtil<>(OK);
    }


    //提交修改请求
    @RequestMapping("save_address")
    public JsonUtil<Void> saveAddressChange(Address address,HttpSession session){
        Address add = (Address) session.getAttribute("address");
        address.setAid(add.getAid());
        addressService.changeAddress(address,getUserNameFromSession(session));
        session.removeAttribute("address");
        return new JsonUtil<>(OK);
    }
}
