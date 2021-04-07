package com.dunshan.mall.cart.domain;

import com.dunshan.mall.model.PmsProduct;
import com.dunshan.mall.model.PmsProductFullReduction;
import com.dunshan.mall.model.PmsProductLadder;
import com.dunshan.mall.model.PmsSkuStock;

import java.util.List;

/**
 * @description: 商品的促销信息，包括sku、打折优惠、满减优惠
 * @author: 李文
 * @create: 2020-11-15 15:36
 **/
public class PromotionProduct extends PmsProduct {
    //商品库存信息
    private List<PmsSkuStock> skuStockList;
    //商品打折信息
    private List<PmsProductLadder> productLadderList;
    //商品满减信息
    private List<PmsProductFullReduction> productFullReductionList;

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }
}
