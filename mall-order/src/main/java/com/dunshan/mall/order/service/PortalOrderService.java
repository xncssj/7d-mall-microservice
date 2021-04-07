package com.dunshan.mall.order.service;

import com.dunshan.mall.common.api.CommonPage;
import com.dunshan.mall.order.domain.ConfirmOrderResult;
import com.dunshan.mall.order.domain.OmsOrderDetail;
import com.dunshan.mall.order.domain.OrderParam;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-11-16 17:06
 * @Description: 订单系统
 */
public interface PortalOrderService {
    /**
     * 根据用户购物车信息生成确认单信息
     * @param cartIds
     */
    ConfirmOrderResult generateConfirmOrder(List<Long> cartIds);

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Map<String, Object> generateOrder(OrderParam orderParam);

    /**
     * 支付成功后的回调
     */
    @Transactional
    Integer paySuccess(Long orderId, Integer payType);

    /**
     * 自动取消超时订单
     */
    @Transactional
    Integer cancelTimeOutOrder();

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

    /**
     * 发送延迟消息取消订单
     */
    void sendDelayMessageCancelOrder(Long orderId);

    /**
     * 确认收货
     */
    void confirmReceiveOrder(Long orderId);

    /**
     * 分页获取用户订单
     */
    CommonPage<OmsOrderDetail> list(Integer status, Integer pageNum, Integer pageSize);


    /**
     * 走redis数据
     * 分页获取用户订单
     */
    CommonPage<OmsOrderDetail> listredis(Integer status, Integer pageNum, Integer pageSize);

    /**
     * 根据订单ID获取订单详情
     */
    OmsOrderDetail detail(Long orderId);
    /**
     * 走根据订单ID获取订单详情
     */
    OmsOrderDetail detailRedis(Long orderId);

    /**
     * 用户根据订单ID删除订单
     */
    void deleteOrder(Long orderId);


}
