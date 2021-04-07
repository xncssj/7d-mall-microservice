package com.dunshan.mall.portal.service;

import com.dunshan.mall.portal.domain.OmsOrderReturnApplyParam;

/**
 * 订单退货管理Service
 * Created by dunshan on 2018/10/17.
 */
public interface OmsPortalOrderReturnApplyService {
    /**
     * 提交申请
     */
    int create(OmsOrderReturnApplyParam returnApply);
}
