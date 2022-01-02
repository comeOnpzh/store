package com.example.store.controller;

import com.example.store.controller.ex.*;
import com.example.store.service.ex.*;
import com.example.store.util.JsonUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
public class BaseController {
    public final static Integer OK = 200;

    //自动将异常对象传递到此方法的参数中
    //当项目中产生异常，会被统一拦截到此方法上，此时这个方法就是请求处理方法，所以要返回一个json
    @ExceptionHandler({ServiceExcetion.class, FileUpLoadException.class})
    public JsonUtil<Void> exceptionHandler(Throwable throwable) {
        JsonUtil<Void> result = new JsonUtil<>(throwable);
        if (throwable instanceof UserNameIsExitException) {
            result.setState(4000);
            result.setMessage("用户名已经存在");
        } else if (throwable instanceof InsertDataException) {
            result.setMessage("插入数据时产生异常");
            result.setState(4001);
        } else if (throwable instanceof NoSuchUserNameException) {
            result.setState(4002);
            result.setMessage("用户不存在");
        } else if (throwable instanceof PasswordNotMatchException) {
            result.setState(4003);
            result.setMessage("用户名或者密码错误，请重新输入");
        } else if (throwable instanceof UpdateException) {
            result.setState(5000);
            result.setMessage(throwable.getMessage());
        } else if (throwable instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("上传的文件不能为空，请重新上传");
        } else if (throwable instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("上传的文件太大，请重新上传");
        } else if (throwable instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("上传的文件类型不对，请重新上传");
        } else if (throwable instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("上传的文件状态出错，请重新上传");
        } else if (throwable instanceof FileUpLoadIOException) {
            result.setState(6999);
            result.setMessage("文件上传过程中出现未知的异常，请重新上传");
        } else if (throwable instanceof AddressCountException) {
            result.setState(7001);
            result.setMessage("收货地址已经多于20个了，无法再新增收货地址!");
        } else if (throwable instanceof DeleteException) {
            result.setState(4007);
            result.setMessage(throwable.getMessage());
        } else if (throwable instanceof DeleteDeniedException) {
            result.setState(4008);
            result.setMessage(throwable.getMessage());
        }else if(throwable instanceof NoSuchAddressException){
            result.setState(4009);
            result.setMessage("该收货地址不存在！请重新选择");
        }else if (throwable instanceof NoSuchProductException){
            result.setState(4010);
            result.setMessage("商品不存在！请重新选择！");
        }
        return result;
    }

    /**
     * @param session 当前会话
     * @return uid
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * @param session
     * @return username
     */
    protected final String getUserNameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
