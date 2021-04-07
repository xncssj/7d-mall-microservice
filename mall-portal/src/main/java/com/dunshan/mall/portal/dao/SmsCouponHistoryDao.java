package com.dunshan.mall.portal.dao;

import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.portal.domain.SmsCouponHistoryDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员优惠券领取历史自定义Dao
 * Created by dunshan on 2018/8/29.
 */
@Repository
public interface SmsCouponHistoryDao {
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);
}
