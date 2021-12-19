package com.example.store.service.impl;

import com.example.store.controller.ex.FileUpLoadIOException;
import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.service.IUserService;
import com.example.store.service.ex.NoSuchUserNameException;
import com.example.store.service.ex.PasswordNotMatchException;
import com.example.store.service.ex.UpdateException;
import com.example.store.service.ex.UserNameIsExitException;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {                 //注册
        //先检查用户名是否已经被注册
        User exitUser = userMapper.isExitUser(user.getUsername());
        if(exitUser!=null){          //用户已经存在
            throw  new UserNameIsExitException("用户名已经存在，请重新输入");
        }
        //用户名不存在
        //1、首先对密码进行md5加密
        String oldPasswd = user.getPassword();
        //得到随机盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值进行加密
        String md5Password = getMd5PassWd(oldPasswd, salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setUserStatus(0);        //用户状态
        Date date = new Date();        //当前时间
        user.setCreatedTime(date);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);
        userMapper.insertUser(user);
    }



    //md5加密方法
    private String getMd5PassWd(String password,String salt){
        //对密码进行三次加密
        for(int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    //登录校验
    @Override
    public User loginCheck(String username, String password) {
        User user = userMapper.isExitUser(username);
        if(user==null){     //查询出来的用户名为空，用户不存在
            throw new NoSuchUserNameException("用户不存在，请重新输入!");
        }
        //从返回的user对象中获取获取和md5密码
        String salt = user.getSalt();
        String userPassword = user.getPassword();
        //将盐值和前台传过来的进行加密
        String md5PassWd = getMd5PassWd(password, salt);
        if(!userPassword.equals(md5PassWd)){       //加密后的密码和数据库中的一致，密码正确
            throw new PasswordNotMatchException("密码错误，请重新输入！") ;
        }
        if(user.getUserStatus()==1){
            throw new NoSuchUserNameException("用户不存在，请先注册！") ;
        }
        return user;
    }

    //密码修改检查
    @Override
    public void changePassword(Integer uid,String oldPassword,String newPassword) {
        //首先要检查输入的原密码是否正确
        User user = getInfosByUid(uid);
        String oldMd5Password = user.getPassword();         //数据库中的md5用户密码
        String oldSalt = user.getSalt();
        String md5PassWd = getMd5PassWd(oldPassword, oldSalt);    //对用户输入的旧密码进行md5加密
        if(!oldMd5Password.equals(md5PassWd)){
            throw new PasswordNotMatchException("原密码输入错误，请重新输入");
        }
        //原密码正确、开始修改密码
        //得到随机盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newPasswordMd5 = getMd5PassWd(newPassword, salt);

        Integer row = userMapper.changePassword(uid, newPasswordMd5, user.getUsername(), new Date());
        if(row!=1){
            throw new UpdateException("更新数据时发生未知的异常");
        }
    }


    /**
     *更新用户信息
     * @param updateUser
     *
     */
    @Override
    public void updateUserInfo(User updateUser) {
        getInfosByUid(updateUser.getUid());
        //用户正常、执行更新操作
        Integer row = userMapper.updateUserInfo(updateUser);
        if(row!=1){
            throw new UpdateException("数据更新时发生未知的异常，请联系管理员处理");
        }
    }

    //通过uid查询用户的所有信息
    @Override
    public User getInfosByUid(Integer Uid) {
        User resUser = userMapper.getUserInfoByUid(Uid);
        if(null==resUser||resUser.getUserStatus()==1){        //用户不存在或者已经注销
            throw new NoSuchUserNameException("用户不存在或者已经注销，请重新注册或者联系管理员");
        }
        return resUser;
    }

    //上传用户头像
    @Override
    public void uploadUserAvatar(Integer uid, String avatar, String userName) {
        Integer row = userMapper.upLoadAvatar(uid, avatar, userName, new Date());
        if(row!=1){
            throw new FileUpLoadIOException("用户头像上传出错，请联系管理员处理");
        }
    }


}
