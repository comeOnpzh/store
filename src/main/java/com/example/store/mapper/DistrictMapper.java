package com.example.store.mapper;

import com.example.store.entity.District;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/23.
 */
public interface DistrictMapper {
    List<District> findByParent(Integer parent);

    String getNameBycode(Integer code);
}
