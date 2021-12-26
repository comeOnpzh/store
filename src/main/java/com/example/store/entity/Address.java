package com.example.store.entity;

import java.util.Objects;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 */
public class Address extends BaseEntity{

    private Integer aid;        //地址id
    private Integer uid;          //用户id
    private String  name;     //用户名
    private String provinceName;         //省-名称
    private Integer provinceCode;       //省-行政代号
    private String cityName;       //市-名称
    private Integer cityCode;      //市-行政代码
    private String areaName;      //区-名称
    private Integer areaCode;       //区-行政代码
    private String zip;         //邮政编码
    private String address;         //详细地址
    private String phone;        // 手机
    private String tel;       //固话
    private String tag;      //标签(家，公司，学校等)
    private Integer isDefault;         //是否默认

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address1 = (Address) o;
        return aid.equals(address1.aid) && uid.equals(address1.uid) && name.equals(address1.name) && provinceName.equals(address1.provinceName) && provinceCode.equals(address1.provinceCode) && cityName.equals(address1.cityName) && cityCode.equals(address1.cityCode) && areaName.equals(address1.areaName) && areaCode.equals(address1.areaCode) && zip.equals(address1.zip) && address.equals(address1.address) && phone.equals(address1.phone) && tel.equals(address1.tel) && tag.equals(address1.tag) && isDefault.equals(address1.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), aid, uid, name, provinceName, provinceCode, cityName, cityCode, areaName, areaCode, zip, address, phone, tel, tag, isDefault);
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
