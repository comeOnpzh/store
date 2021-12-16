package com.example.store.service;

import com.example.store.entity.User;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
public interface IUserService {
    void register(User user);
    User loginCheck(String username,String password);
    void changePassword(Integer uid,String oldPassword,String newPassword);
    void updateUserInfo(User user);
    User getInfosByUid(Integer Uid);
}
