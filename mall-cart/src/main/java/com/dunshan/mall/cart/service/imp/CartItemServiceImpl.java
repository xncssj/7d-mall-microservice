package com.dunshan.mall.cart.service.imp;

import cn.hutool.core.collection.CollUtil;
import com.dunshan.mall.cart.dao.PortalProductDao;
import com.dunshan.mall.cart.domain.CartProduct;
import com.dunshan.mall.cart.domain.CartPromotionItem;
import com.dunshan.mall.cart.feign.CartPromotionService;
import com.dunshan.mall.cart.feign.MemberService;
import com.dunshan.mall.cart.service.CartItemService;
import com.dunshan.mall.mapper.OmsCartItemMapper;
import com.dunshan.mall.mapper.PmsProductMapper;
import com.dunshan.mall.mapper.PmsSkuStockMapper;
import com.dunshan.mall.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 购物车业务实现类
 * @author: 李文
 * @create: 2020-11-15 14:10
 **/
@Service
public class CartItemServiceImpl  implements CartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private PortalProductDao productDao;
    @Autowired
    CartPromotionService calcCartPromotion;

    @Autowired
    private MemberService memberService;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductMapper productMapper;

    /**
     * 查询购物车中是否包含该商品，有增加数量，无添加到购物车
     *
     * @param cartItem
     * @return
     */
    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        //如果没有就插入数据
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            //有数据就更新商品数量
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }
    /**
     * 增加购物车
     * @param productSkuCode 库存商品编号
     * @param quantity 商品数量
     * @return
     */
    @Override
    public int addCart(String productSkuCode, Integer quantity) {
        int count;
        PmsSkuStockExample example = new PmsSkuStockExample();
        example.createCriteria().andSkuCodeEqualTo(productSkuCode);
        List<PmsSkuStock> pmsSkuStocks = skuStockMapper.selectByExample(example);
        if (pmsSkuStocks == null) {
            return 0;
        }
        PmsSkuStock pmsSkuStock = pmsSkuStocks.get(0);
        PmsProduct pmsProduct = productMapper.selectByPrimaryKey(pmsSkuStock.getProductId());
        OmsCartItem cartItem = new OmsCartItem();
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        cartItem.setProductId(pmsSkuStock.getProductId());
        cartItem.setProductSkuId(pmsSkuStock.getId());
        cartItem.setProductSkuCode(productSkuCode);
        cartItem.setQuantity(quantity);
        cartItem.setProductSkuCode(pmsSkuStock.getSkuCode());
        cartItem.setPrice(pmsProduct.getPrice());
        cartItem.setProductPic(pmsProduct.getPic());
        cartItem.setProductName(pmsProduct.getName());
        cartItem.setProductSubTitle(pmsProduct.getSubTitle());
        cartItem.setCreateDate(new Date());
        cartItem.setModifyDate(new Date());
        cartItem.setProductCategoryId(pmsProduct.getProductCategoryId());
        cartItem.setProductBrand(pmsProduct.getBrandName());
        cartItem.setProductSn(pmsProduct.getProductSn());
        cartItem.setProductAttr(pmsSkuStock.getSpData());
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }

        return count;
    }

    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId()).andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(cartItem.getProductSkuId())) {
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    /**
     * 根据会有id查询购物车信息
     * 购物车数据
     *
     * @param memberId 会员id
     * @return
     */
    @Override
    public List<OmsCartItem> list(Long memberId) {
        if (memberId == null) {
            return null;
        }
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0).andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }

    /**
     * 获取包含促销活动信息的购物车列表
     *
     * @param memberId 用户id号
     * @param cartIds  购物车id
     * @return
     */
    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds) {

        //根据购买的用户id查询购物车数据
        List<OmsCartItem> cartItemList = list(memberId);
        if (cartItemList == null) {
            return null;
        }

        //通过传入的购物车id过滤购物车数据
        if (CollUtil.isNotEmpty(cartIds)) {
            cartItemList = cartItemList.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }

        //购物车中促销信息的封装
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(cartItemList)) {
            // 计算购物车中的促销活动信息
            cartPromotionItemList = calcCartPromotion.calcCartPromotion(cartItemList);
        }
        return cartPromotionItemList;
    }

    /**
     * 修改某个购物车商品的数量
     *
     * @param id
     * @param memberId
     * @param quantity
     * @return
     */
    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andIdEqualTo(id).andMemberIdEqualTo(memberId);
//        return cartItemMapper.updateByExampleSelective(cartItem, example);
        return cartItemMapper.updataCateNumb(id, quantity);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);

        for (Long id : ids) {
            cartItemMapper.deleteByPrimaryKey(id);
        }
        //cartItemMapper.updateByExampleSelective(record, example);
        return ids.size();
    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        //删除原购物车信息
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }


}

