package com.example.store.config;

import com.example.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengzh5 Cotter on 2021/12/8.
 * 拦截器注册类、需要实现WebMvcConfigurer接口，拦截器必须要在这个类注册
 * 可以在这个类对拦截器进行白名单和黑名单的设置
 */
@Configuration     //这个注解表示这个类会被作为springboot的注解类，会在服务器启动时自动加载
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //只需要实现这个方法就可以了
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor loginInterceptor = new LoginInterceptor();          //获取需要注册的拦截器类
        List path = new ArrayList<>();
        path.add("/bootstrap3/**");
        path.add("/css/**");
        path.add("/images/**");
        path.add("/js/**");
        path.add("/web/login.html");
        path.add("/web/product.html");
        path.add("/web/register.html");
        path.add("/user/login");
        path.add("/user/register");
        path.add("/product/**");


        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")                 //需要拦截的路径
                .excludePathPatterns(path);                 //需要放行的路径
    }
}
