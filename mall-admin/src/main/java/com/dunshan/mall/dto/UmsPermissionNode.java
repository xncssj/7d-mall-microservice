package com.dunshan.mall.dto;

import com.dunshan.mall.model.UmsPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 后台权限节点封装
 * Created by dunshan on 2018/9/30.
 */
public class UmsPermissionNode extends UmsPermission {
    @Getter
    @Setter
    @ApiModelProperty(value = "子级权限")
    private List<UmsPermissionNode> children;
}
