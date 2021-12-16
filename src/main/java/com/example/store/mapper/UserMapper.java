package com.example.store.mapper;

import com.example.store.entity.User;

import java.util.Date;

/**
 * Created by pengzh5 Cotter on 2021/12/2.
 */
public interface UserMapper    {
    Integer insertUser(User user);            //保存注册的用户信息
    User isExitUser(String  username);           //根据用户名查询
    Integer changePassword(Integer uid, String newPassword, String modifiedUser, Date modifiedTime);         //通过uid修改用户密码
    User getUserInfoByUid(Integer uid);          //通过uid查询用户信息
    Integer updateUserInfo(User user);
    Integer upLoadAvatar(Integer uid,String avatar);           //更新用户头像

}
