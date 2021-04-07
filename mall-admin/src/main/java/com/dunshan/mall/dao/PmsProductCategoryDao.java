package com.dunshan.mall.dao;

import com.dunshan.mall.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 商品分类自定义Dao
 * Created by dunshan on 2018/5/25.
 */
public interface PmsProductCategoryDao {
    /**
     * 获取商品分类及其子分类
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
