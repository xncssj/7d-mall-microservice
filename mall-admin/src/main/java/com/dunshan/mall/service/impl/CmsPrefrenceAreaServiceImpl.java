package com.dunshan.mall.service.impl;

import com.dunshan.mall.mapper.CmsPrefrenceAreaMapper;
import com.dunshan.mall.model.CmsPrefrenceArea;
import com.dunshan.mall.model.CmsPrefrenceAreaExample;
import com.dunshan.mall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优选Service实现类
 * Created by dunshan on 2018/6/1.
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {
    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
