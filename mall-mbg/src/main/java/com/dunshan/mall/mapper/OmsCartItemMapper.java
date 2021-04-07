package com.dunshan.mall.mapper;

import com.dunshan.mall.model.OmsCartItem;
import com.dunshan.mall.model.OmsCartItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsCartItemMapper {
    long countByExample(OmsCartItemExample example);

    int deleteByExample(OmsCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem record);

    int insertSelective(OmsCartItem record);

    /**
     * oms_cart_item
     *
     * @param example
     * @return
     */
    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByExample(@Param("record") OmsCartItem record, @Param("example") OmsCartItemExample example);

    int updateByPrimaryKeySelective(OmsCartItem record);

    int updateByPrimaryKey(OmsCartItem record);

    @Update("update oms_cart_item set quantity=#{quantity} WHERE id = #{id}")
    int updataCateNumb(Long id, Integer quantity);
}