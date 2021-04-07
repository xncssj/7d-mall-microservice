package com.dunshan.mall;


import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.dunshan.mall.dao.PmsMemberPriceDao;
import com.dunshan.mall.dao.PmsProductDao;
import com.dunshan.mall.dto.PmsProductResult;
import com.dunshan.mall.model.PmsMemberPrice;
import com.dunshan.mall.model.UmsMember;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsDaoTests {
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsProductDao productDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsDaoTests.class);
    @Test
    @Transactional
    @Rollback
    public void testInsertBatch(){
        List<PmsMemberPrice> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            PmsMemberPrice memberPrice = new PmsMemberPrice();
            memberPrice.setProductId(1L);
            memberPrice.setMemberLevelId((long) (i+1));
            memberPrice.setMemberPrice(new BigDecimal("22"));
            list.add(memberPrice);
        }
        int count = memberPriceDao.insertList(list);
        Assert.assertEquals(5,count);
    }

    @Test
    public void  testGetProductUpdateInfo(){
        PmsProductResult productResult = productDao.getUpdateInfo(7L);
        String json = JSONUtil.parse(productResult).toString();
        LOGGER.info(json);
    }


    @Test
    public void getLoginTst() {
        for (int i = 0; i < 10; i++) {
            UmsMember umsMember = new UmsMember();
            umsMember.setUsername("username");
            umsMember.setPhone("19210992070");
            umsMember.setPassword(BCrypt.hashpw("123456"));
            umsMember.setCreateTime(new Date());
            umsMember.setStatus(1);
            System.out.println(JSON.toJSON(umsMember));
        }

    }
}
