package com.dunshan.mall.dao;

import com.dunshan.mall.model.UmsMenu;
import com.dunshan.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台角色管理Dao
 * Created by dunshan on 2020/2/2.
 */
public interface UmsRoleDao {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
