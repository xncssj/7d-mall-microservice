package com.dunshan.mall.dao;

import com.dunshan.mall.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员价格Dao
 * Created by dunshan on 2018/4/26.
 */
public interface PmsMemberPriceDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
