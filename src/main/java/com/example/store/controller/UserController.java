package com.example.store.controller;

import com.example.store.controller.ex.FileEmptyException;
import com.example.store.controller.ex.FileSizeException;
import com.example.store.controller.ex.FileTypeException;
import com.example.store.controller.ex.FileUpLoadIOException;
import com.example.store.entity.BaseEntity;
import com.example.store.entity.User;
import com.example.store.service.IUserService;
import com.example.store.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
//@CrossOrigin           //这个注解表示这个类可以接受跨域访问
@RestController           //这个注解表示这个类放回到前端的数据将会是json格式
@RequestMapping("user")          //这个注解表示这个类将会捕获以user为路径的请求
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("register")
    public JsonUtil<Void> register(User user){
        //调用业务层代码
        userService.register(user);
        return new JsonUtil<>(OK);
    }

    //登录检查
    @RequestMapping("login")
    public JsonUtil<User> login(String username, String password,
                                HttpSession session){   //springboot对session进行了封装，只要服务器一启动，就会创建一个全局的session对象，可全局访问，不过需要把httpSession作为请求方法的一个参数
        User user = userService.loginCheck(username, password);
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        return new JsonUtil<User>(OK,user);
    }

    @RequestMapping("change_password")
    public JsonUtil<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid");
        String username = (String) session.getAttribute("username");
        userService.changePassword(uid,oldPassword,newPassword);
        return new JsonUtil<>(OK);
    }

    @RequestMapping("update_Infos")
    public JsonUtil<Void> updateInfos(User user,HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid");
        user.setUid(uid);
        userService.updateUserInfo(user);
        return new JsonUtil<>(OK);
    }

    @RequestMapping("get_Infos")
    public JsonUtil<User> getInfosByUid(HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid");
        User user = userService.getInfosByUid(uid);
        return new JsonUtil<>(OK,user);
    }

    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    public static final List<String> AVATAR_TYPE  = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
     *
     * @param session
     * @param file      MultipartFile是springMVC的一个整合的一个类，这个类可以接受前台传过来的所有文件类型的参数，springboot
     *                  也整合了这个类，只需要在参数列表里面声明一个MultipartFile就可以使用，可以配合@RequestParam注解使用
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonUtil<String> uploadAvatar(HttpSession session, @RequestParam("file") MultipartFile file){
        //判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException();
        }
        //判断文件大小
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException();
        }
        //判断文件类型
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException();
        }
        //文件正常
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        File dir = new File(parent);       //将一个file对象指向这个路径
        if(!dir.exists()){          //判断目录名是否已经存在
            dir.mkdirs();                //创建该目录
        }

        String originalFilename = file.getOriginalFilename();   //这个方法可以获取到一个文件名+文件后缀的字符串
        //保存文件后缀并且生成一个随机文件
        int index = originalFilename.lastIndexOf(".");//获取到.的位置
        String suffix = originalFilename.substring(index);//获取文件的后缀，subString可以返回index到最后的字符（包括index）
        String randomFileName = UUID.randomUUID().toString().toUpperCase();     //获取到随机文件名
        //将随机文件名和文件后缀进行拼接
        String newFileName = randomFileName + suffix;
        //生成一个空的文件
        File dest = new File(dir, newFileName);
        //将参数中获取到的文件写入dest中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUpLoadIOException("文件读写时发生异常");
        }
        //将文件路径保存到数据库中
        String avatar = "/upload/"+newFileName;
        Integer uid = getUidFromSession(session);
        String userName = getUserNameFromSession(session);
        userService.uploadUserAvatar(uid,avatar,userName);
        return new JsonUtil<>(OK,avatar);
    }
}
