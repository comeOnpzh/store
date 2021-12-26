package com.example.store.entity;

/**
 * Created by pengzh5 Cotter on 2021/12/23.
 */
public class District {
    private Integer Id;
    private Integer parent;
    private Integer code;
    private String name;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "District{" +
                "Id=" + Id +
                ", parent=" + parent +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
