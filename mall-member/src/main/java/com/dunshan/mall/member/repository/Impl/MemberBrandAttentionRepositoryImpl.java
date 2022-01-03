package com.dunshan.mall.member.repository.Impl;

import com.dunshan.mall.common.utls.PagaUtls;
import com.dunshan.mall.member.domain.MemberBrandAttention;
import com.dunshan.mall.member.repository.MemberBrandAttentionRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author dunshan
 * @program: mall-swarm-new
 * @description: 会员关注
 * @date 2021-11-15 11:05:02
 * 1
 */
@Service
public class MemberBrandAttentionRepositoryImpl implements MemberBrandAttentionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MemberBrandAttention findByMemberIdAndBrandId(Long memberId, Long brandId) {
        Query query = new Query(Criteria.where("id").is(memberId).and("brandId").is(brandId));
        return mongoTemplate.findOne(query, MemberBrandAttention.class);

    }

    @Override
    public long deleteByMemberIdAndBrandId(Long memberId, Long brandId) {
        Query query = new Query(Criteria.where("id").is(memberId).and("brandId").is(brandId));
        DeleteResult remove = mongoTemplate.remove(query, MemberBrandAttention.class);
        long deletedCount = remove.getDeletedCount();
        return deletedCount;
    }

    @Override
    public Page<MemberBrandAttention> findByMemberId(Long memberId, Pageable pageable) {
        Query query = new Query(Criteria.where("id").is(memberId));
        //设置起始数
        query.skip(pageable.getOffset() - 1);
        //设置查询条数
        query.limit(pageable.getPageSize());
        List<MemberBrandAttention> memberBrandAttentions = mongoTemplate.find(query, MemberBrandAttention.class);
        return PagaUtls.listConvertToPage(memberBrandAttentions, pageable);
    }

    @Override
    public void deleteAllByMemberId(Long memberId) {
        mongoTemplate.remove(Criteria.where("memberId").is(memberId));
    }

    @Override
    public void save(MemberBrandAttention memberBrandAttention) {
        mongoTemplate.save(memberBrandAttention);
    }


}
