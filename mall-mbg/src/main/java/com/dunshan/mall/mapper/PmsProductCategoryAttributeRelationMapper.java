package com.dunshan.mall.mapper;

import com.dunshan.mall.model.PmsProductCategoryAttributeRelation;
import com.dunshan.mall.model.PmsProductCategoryAttributeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductCategoryAttributeRelationMapper {
    long countByExample(PmsProductCategoryAttributeRelationExample example);

    int deleteByExample(PmsProductCategoryAttributeRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategoryAttributeRelation record);

    int insertSelective(PmsProductCategoryAttributeRelation record);

    List<PmsProductCategoryAttributeRelation> selectByExample(PmsProductCategoryAttributeRelationExample example);

    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    int updateByExample(@Param("record") PmsProductCategoryAttributeRelation record, @Param("example") PmsProductCategoryAttributeRelationExample example);

    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);
}