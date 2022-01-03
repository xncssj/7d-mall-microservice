package com.dunshan.mall.member.repository;

import com.dunshan.mall.member.domain.MemberBrandAttention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 会员关注Repository
 * Created by dunshan on 2018/8/2.
 *  extends MongoRepository<MemberBrandAttention,String>
 *      1
 */
public interface MemberBrandAttentionRepository {
    MemberBrandAttention findByMemberIdAndBrandId(Long memberId, Long brandId);
    long deleteByMemberIdAndBrandId(Long memberId, Long brandId);
    Page<MemberBrandAttention> findByMemberId(Long memberId, Pageable pageable);
    void deleteAllByMemberId(Long memberId);

    void save(MemberBrandAttention memberBrandAttention);

}
