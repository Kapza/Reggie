package com.hga.reggie.entity;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.convert.ExcelDataConvert;
import cn.gjing.tools.excel.write.valid.ExcelDateValid;
import cn.gjing.tools.excel.write.valid.ExcelNumericValid;
import cn.gjing.tools.excel.write.valid.ValidType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Excel
public class Dish {
    @ExcelField(value = "菜品id")
    private Long id;

    //菜品名称
    @ExcelField(value = "菜品名称")
    private String name;

    //菜品分类id
    @ExcelField(value = "菜品分类id")
    private Long categoryId;

    //菜品价格
    @ExcelField(value = "菜品价格")
    private BigDecimal price;

    //商品码
    @ExcelField(value ="商品码")
    private String code;

    //图片
    @ExcelField(value = "图片")
    private String image;

    //描述信息
    @ExcelField(value = "描述信息")
    private String description;

    //0 停售 1 起售
    @ExcelField(value = "0 停售 1 起售")
    private Integer status;

    //顺序
    private Integer sort;

    @ExcelField(value = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ExcelField(value = "更新时间",format = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ExcelField(value = "创建用户")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ExcelField(value = "更新用户")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
