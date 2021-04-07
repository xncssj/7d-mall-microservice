package com.dunshan.mall.dao;

import com.dunshan.mall.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 自定义商品属性分类Dao
 * Created by dunshan on 2018/5/24.
 */
public interface PmsProductAttributeCategoryDao {
    /**
     * 获取包含属性的商品属性分类
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
