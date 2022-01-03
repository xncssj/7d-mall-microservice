package com.dunshan.mall.common.utls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dunshan
 * @program: mall-swarm-new
 * @description: 页面转换
 * @date 2021-11-15 14:45:36
 * 1
 */
public class PagaUtls {

    /**
     * 页面转换
     *
     * @param list
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        // 当前页第一条数据在List中的位置
        int start = (int) pageable.getOffset();
        // 当前页最后一条数据在List中的位置
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        // 配置分页数据
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
