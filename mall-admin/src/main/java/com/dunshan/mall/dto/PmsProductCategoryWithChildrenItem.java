package com.dunshan.mall.dto;

import com.dunshan.mall.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by dunshan on 2018/5/25.
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Getter
    @Setter
    @ApiModelProperty("子级分类")
    private List<PmsProductCategory> children;
}
