package com.example.store.mapper;

import com.example.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 */
public interface AddressMapper {
    Integer getAddressAccount(Integer uid);

    Integer insertAdd(Address add);

    List<Address> findAllAddress(Integer uid);

    Integer updateNonDefault(Integer uid);

    Integer updateIsDefault(Integer aid, String modifiedUser, Date modifiedDate);

    Integer deleteAddress(Integer aid);

    Address findByAid(Integer aid);

    Integer changeAddress(Address address);
}
