package com.dunshan.mall.mapper;

import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberMapper {
    long countByExample(UmsMemberExample example);

    int deleteByExample(UmsMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    List<UmsMember> selectByExample(UmsMemberExample example);

    UmsMember selectByPrimaryKey(Long id);

    List<UmsMember> selectLimit(@Param("start") Long start, @Param("end") Long end);

    int updateByExampleSelective(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);

    int updateByExample(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    int insertUmsMember(List<UmsMember> userList);
}