package com.example.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pengzh5 Cotter on 2021/12/8.
 * 登录拦截器、需要实现HandlerInterceptor接口
 */
public class LoginInterceptor implements HandlerInterceptor {
    //在请求开始之前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if(uid==null){
            //session对象中没有已经登录的用户信息，用户还没有登录，跳转到登录页面
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }

    //在请求完成之后进行拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //在页面渲染完成之后进行拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
