package com.example.store.controller;

import com.example.store.entity.District;
import com.example.store.service.IDistrictService;
import com.example.store.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/23.
 */
@RestController
@RequestMapping("district")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/", ""})
    public JsonUtil<List> getDistrictByParent(Integer parent) {
        List<District> district = districtService.getDistrictByParent(parent);
        return new JsonUtil<List>(OK, district);
    }
}
