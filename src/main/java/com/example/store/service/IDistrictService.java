package com.example.store.service;

import com.example.store.entity.District;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/23.
 */
public interface IDistrictService {
    List<District> getDistrictByParent(Integer parent);

    String getNameByCode(Integer code);
}
