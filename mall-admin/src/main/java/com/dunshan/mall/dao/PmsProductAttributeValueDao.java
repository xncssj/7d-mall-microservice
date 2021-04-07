package com.dunshan.mall.dao;

import com.dunshan.mall.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品参数，商品自定义规格属性Dao
 * Created by dunshan on 2018/4/26.
 */
public interface PmsProductAttributeValueDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list")List<PmsProductAttributeValue> productAttributeValueList);
}
