package com.hga.reggie.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 */
@Data
public class R<T> {
    private Integer code;// 1 成功，0是失败
    private String msg;//错误信息
    private T data;//数据
    private Map<Object, Object> map=new HashMap<>();

    public static <T> R<T> success(T object){
        R<T> r =new R<T>();
        r.data=object;
        r.code=1;
        return r;
    }

    public static <T> R<T> error(String msg){
        R<T> r=new R<>();
        r.msg=msg;
        r.code=0;
        return r;
    }

    public R<T> add(String key, Object value){
        this.map.put(key,value);
        return this;
    }
}
