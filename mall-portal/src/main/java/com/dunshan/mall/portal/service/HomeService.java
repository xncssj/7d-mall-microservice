package com.dunshan.mall.portal.service;

import com.dunshan.mall.model.CmsSubject;
import com.dunshan.mall.model.PmsProduct;
import com.dunshan.mall.model.PmsProductCategory;
import com.dunshan.mall.portal.domain.HomeContentResult;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 首页内容管理Service
 * Created by dunshan on 2019/1/28.
 */
public interface HomeService {

    /**
     * 获取首页内容
     */
    HomeContentResult content(Integer page, Integer size);

    /**
     * 首页商品推荐
     */
    List<PmsProduct> recommendProductList(Integer pageSize, Integer pageNum);

    /**
     * 获取商品分类
     * @param parentId 0:获取一级分类；其他：获取指定二级分类
     */
    List<PmsProductCategory> getProductCateList(Long parentId);

    /**
     * 根据专题分类分页获取专题
     * @param cateId 专题分类id
     */
    List<CmsSubject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum);

    /**
     * 分页获取人气推荐商品
     */
    List<PmsProduct> hotProductList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取新品推荐商品
     */
    List<PmsProduct> newProductList(Integer pageNum, Integer pageSize);

    HomeContentResult contentnew();
}
