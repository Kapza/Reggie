package com.hga.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hga.reggie.dto.DishDto;
import com.hga.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品同时插入口味数据
    public  void saveWithFlavor(DishDto dishDto);
    //根据菜品id查询菜品信息
    public DishDto getByIdWithFlavor(Long id);
    //更新菜品信息，同时更新口味信息
    void updateWithFlavor(DishDto dishDto);
}
