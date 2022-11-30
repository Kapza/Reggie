package com.hga.reggie.common;

/**
 * 基于TreadLocal封装的工具类，用于保存当前登录用户的id
 */
public class BaseContext {
    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
