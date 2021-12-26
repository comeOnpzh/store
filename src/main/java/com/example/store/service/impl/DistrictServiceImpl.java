package com.example.store.service.impl;

import com.example.store.entity.District;
import com.example.store.mapper.DistrictMapper;
import com.example.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/23.
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getDistrictByParent(Integer parent) {
        List<District> res = districtMapper.findByParent(parent);
        /*
        为了减少传输成本，所以把不需要的信息去掉，保留有用的信息,只需要保留code和name即可
         */
        for (District district : res) {
            district.setParent(null);
            district.setId(null);
        }
        return res;
    }

    @Override
    public String getNameByCode(Integer code) {
        String name = districtMapper.getNameBycode(code);
        return name;
    }
}
