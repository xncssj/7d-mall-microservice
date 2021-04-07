package com.dunshan.mall.search.dao;

import com.dunshan.mall.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 * Created by dunshan on 2018/6/19.
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
