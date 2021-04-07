package com.dunshan.mall.cart.dao;

import com.dunshan.mall.cart.domain.CartProduct;
import com.dunshan.mall.cart.domain.PromotionProduct;
import com.dunshan.mall.model.SmsCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PortalProductDao {

    CartProduct getCartProduct(@Param("id") Long id);

    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId") Long productCategoryId);
}
