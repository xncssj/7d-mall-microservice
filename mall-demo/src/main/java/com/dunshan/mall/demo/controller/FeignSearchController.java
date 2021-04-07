package com.dunshan.mall.demo.controller;

import com.dunshan.mall.common.api.CommonResult;
import com.dunshan.mall.demo.service.FeignSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Feign调用mall-search接口示例
 * Created by dunshan on 2019/10/22.
 */
@Api(tags = "FeignSearchController", description = "Feign调用mall-search接口示例")
@RestController
@RequestMapping("/feign/search")
public class FeignSearchController {

    @Autowired
    private FeignSearchService feignSearchService;

    @ApiOperation(value = "简单商品搜索")
    @RequestMapping(value = "/justSearch", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {

        return feignSearchService.search(keyword, pageNum, pageSize);
    }
}
