package com.dunshan.mall.cart.domain;

import com.dunshan.mall.model.PmsProduct;
import com.dunshan.mall.model.PmsProductAttribute;
import com.dunshan.mall.model.PmsSkuStock;

import java.util.List;

/**
 * @description: 购物车信息
 * @author: 李文
 * @create: 2020-11-15 14:02
 **/
public class CartProduct extends PmsProduct {
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }
}

