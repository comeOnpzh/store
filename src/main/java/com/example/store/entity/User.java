package com.example.store.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by pengzh5 Cotter on 2021/12/2.
 * 用户类
 * uid int auto_increment comment '用户id',
 * 	username varchar(20) not null unique comment '用户名',
 * 	password CHAR(32) not null comment '密码',
 * 	salt CHAR(36) comment '盐值(密码加密)',
 * 	phone varchar(20) comment '电话号码',
 * 	email varchar(30) comment '电子邮箱',
 * 	gender int comment '性别:0-女，1-男',
 * 	avatar varchar(50) comment '头像',
 * 	user_status  int comment '是否删除：0-未注销，1-已注销',
 */
public class User extends BaseEntity implements Serializable  {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer userStatus;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(avatar, user.avatar) && Objects.equals(userStatus, user.userStatus) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, username, password, salt, phone, email, gender, avatar, userStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
