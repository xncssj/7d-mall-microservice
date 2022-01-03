package com.dunshan.mall.member.repository.Impl;

import com.dunshan.mall.common.utls.PagaUtls;
import com.dunshan.mall.member.domain.MemberReadHistory;
import com.dunshan.mall.member.repository.MemberReadHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dunshan
 * @program: mall-swarm-new
 * @description: 会员商品浏览
 * @date 2021-11-15 11:02:55
 * 1
 */
@Service
public class MemberReadHistoryRepositoryImpl implements MemberReadHistoryRepository {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public Page<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable pageable) {
        Query query = new Query(Criteria.where("id").is(memberId));
        //设置起始数
        query.skip(pageable.getOffset() - 1);
        //设置查询条数
        query.limit(pageable.getPageSize());
        List<MemberReadHistory> memberReadHistories = mongoTemplate.find(query, MemberReadHistory.class);
        return PagaUtls.listConvertToPage(memberReadHistories, pageable);
    }

    @Override
    public void deleteAllByMemberId(Long memberId) {
        mongoTemplate.remove(Criteria.where("memberId").is(memberId));

    }

    @Override
    public void deleteAll(List<MemberReadHistory> deleteList) {
        ArrayList<String> list = new ArrayList<>();
        for (MemberReadHistory memberReadHistory : deleteList) {
            list.add(memberReadHistory.getId());
        }
        Query q = new Query(Criteria.where("id").in(list));
        mongoTemplate.findAllAndRemove(q, MemberReadHistory.class);
    }

    @Override
    public void save(MemberReadHistory memberReadHistory) {
        mongoTemplate.save(memberReadHistory);
    }
}
