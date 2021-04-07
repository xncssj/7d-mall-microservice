package com.dunshan.mall.member.dao;


import com.dunshan.mall.model.SmsCoupon;
import com.dunshan.mall.member.domain.SmsCouponHistoryDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SmsCouponHistoryDao {
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);
}
