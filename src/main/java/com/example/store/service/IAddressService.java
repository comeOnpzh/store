package com.example.store.service;

import com.example.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 */
public interface IAddressService {
    void insertAddress(Integer uid, String username, Address address);

    List<Address> findAlladdress(Integer uid);

    void changeDefaultAddress(Integer uid,Integer aid, String modifiedUser);

    void deleteAddress(Integer aid);

    void changeAddress(Address address,String username);
}
