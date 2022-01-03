package com.dunshan.mall.member.repository.Impl;

import com.dunshan.mall.common.utls.PagaUtls;
import com.dunshan.mall.member.domain.MemberBrandAttention;
import com.dunshan.mall.member.domain.MemberProductCollection;
import com.dunshan.mall.member.repository.MemberProductCollectionRepository;
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
 * @description: 商品收藏
 * @date 2021-11-15 11:04:07
 * 1
 */
@Service
public class MemberProductCollectionRepositoryImpl implements MemberProductCollectionRepository {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MemberProductCollection findByMemberIdAndProductId(Long memberId, Long productId) {
        return null;
    }

    @Override
    public long deleteByMemberIdAndProductId(Long memberId, Long productId) {
        Query query = new Query(Criteria.where("id").is(memberId).and("productId").is(productId));
        DeleteResult remove = mongoTemplate.remove(query, MemberBrandAttention.class);
        long deletedCount = remove.getDeletedCount();
        return deletedCount;
    }

    @Override
    public Page<MemberProductCollection> findByMemberId(Long memberId, Pageable pageable) {
        Query query = new Query(Criteria.where("id").is(memberId));
        //设置起始数
        query.skip(pageable.getOffset() - 1);
        //设置查询条数
        query.limit(pageable.getPageSize());
        List<MemberProductCollection> memberProductCollections = mongoTemplate.find(query, MemberProductCollection.class);
        return PagaUtls.listConvertToPage(memberProductCollections, pageable);
    }

    @Override
    public void deleteAllByMemberId(Long memberId) {
        mongoTemplate.remove(Criteria.where("memberId").is(memberId));
    }

    @Override
    public void save(MemberProductCollection productCollection) {
        mongoTemplate.save(productCollection);
    }
}
