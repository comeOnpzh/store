package com.example.store.service.impl;

import com.example.store.entity.Address;
import com.example.store.mapper.AddressMapper;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IAddressService;
import com.example.store.service.IDistrictService;
import com.example.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IDistrictService districtService;

    @Override
    public void insertAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.getAddressAccount(uid);
        if (count >= 20) {       //收货地址存在大于或者等于20个时，不能再新增收货地址
            throw new AddressCountException();
        }
        address.setUid(uid);
        address.setIsDefault(0);
        address.setCreatedTime(new Date());
        address.setCreatedUser(username);
        //通过市县区的code获取到name
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        Integer row = addressMapper.insertAdd(address);
        if (row != 1) {
            throw new InsertDataException();
        }
    }

    @Override
    public List<Address> findAlladdress(Integer uid) {
        List<Address> allAddress = addressMapper.findAllAddress(uid);
        for (Address address : allAddress) {
            address.setModifiedUser(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
            address.setAreaCode(null);
            address.setCityCode(null);
            address.setProvinceCode(null);
            address.setModifiedTime(null);
            String tag = address.getTag();
            address.setTag(null == tag ? "临时地址" : "1".equals(tag) ? "家" : "2".equals(tag) ? "学校" : "单位");
            address.setAddress(address.getProvinceName() + address.getCityName() + address.getAreaName() + address.getAddress());
        }
        return allAddress;
    }

    //修改用户默认地址方法
    @Override
    public void changeDefaultAddress(Integer uid, Integer aid, String modifiedUser) {
        //首先把该用户所有收货地址设置成非默认
        Integer row = addressMapper.updateNonDefault(uid);
        Integer account = addressMapper.getAddressAccount(uid);
        if (row != account) {
            throw new UpdateException("修改默认地址时出现异常！");
        }
        Integer defaultRow = addressMapper.updateIsDefault(aid, modifiedUser, new Date());
        if (defaultRow != 1) {
            throw new UpdateException("更新数据时出现异常!");
        }
    }

    //删除收货地址
    @Override
    public void deleteAddress(Integer aid) {
        //查询aid是否存在
        Address add = addressMapper.findByAid(aid);
        if (add == null) {
            throw new DeleteDeniedException("非法删除异常！");
        }
        Integer row = addressMapper.deleteAddress(aid);
        if (row != 1) {
            throw new DeleteException("删除地址出现异常");
        }
        //如果要删除的地址是默认收货地址，删除之后取创建时间最近的一个地址变成默认
        if(add.getIsDefault()==1){
            List<Address> addressList = addressMapper.findAllAddress(add.getUid());
            if(addressList!=null||addressList.size()>0){          //如果已经没有收货地址了，则不需要再设置
                //取第一个地址设置成默认
                Address address = addressList.get(0);
                Integer res = addressMapper.updateIsDefault(address.getAid(), address.getname(), new Date());
                System.out.println(res);
            }

        }
    }

    //修改收货地址
    @Override
    public void changeAddress(Address address, String username) {
        Address res = addressMapper.findByAid(address.getAid());
        //判断该aid是否存在
        if(res==null){
            throw new NoSuchAddressException();
        }
        //通过市县区的code获取到name
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        Integer row = addressMapper.changeAddress(address);
        if(row!=1){
            throw new UpdateException();
        }
    }
}
