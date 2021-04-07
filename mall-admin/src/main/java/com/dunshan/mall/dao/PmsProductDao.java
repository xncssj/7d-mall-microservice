package com.dunshan.mall.dao;

import com.dunshan.mall.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;


/**
 * 自定义商品管理Dao
 * Created by dunshan on 2018/4/26.
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
