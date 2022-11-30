package com.hga.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hga.reggie.common.CustomException;
import com.hga.reggie.entity.Category;
import com.hga.reggie.entity.Dish;
import com.hga.reggie.entity.Setmeal;
import com.hga.reggie.mapper.CategoryMapper;
import com.hga.reggie.service.CategoryService;
import com.hga.reggie.service.DishService;
import com.hga.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        //先看是否关联菜品
        LambdaQueryWrapper<Dish> dishqueryWrapper=new LambdaQueryWrapper<>();
        dishqueryWrapper.eq(Dish::getCategoryId,id);
        long count1 = dishService.count(dishqueryWrapper);
        //先看是否关联套餐
        if(count1>0){
            //抛出异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealqueryWrapper=new LambdaQueryWrapper<>();
        setmealqueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2=setmealService.count(setmealqueryWrapper);
        if(count2>0){
            //抛出异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
