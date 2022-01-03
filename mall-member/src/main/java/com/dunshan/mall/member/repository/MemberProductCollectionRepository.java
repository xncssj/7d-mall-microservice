package com.dunshan.mall.member.repository;

import com.dunshan.mall.member.domain.MemberProductCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 商品收藏Repository
 * Created by dunshan on 2018/8/2.
 * extends MongoRepository<MemberProductCollection,String>
 *     1
 */
public interface MemberProductCollectionRepository {
    MemberProductCollection findByMemberIdAndProductId(Long memberId, Long productId);
    long deleteByMemberIdAndProductId(Long memberId, Long productId);
    Page<MemberProductCollection> findByMemberId(Long memberId, Pageable pageable);
    void deleteAllByMemberId(Long memberId);

    void save(MemberProductCollection productCollection);

}
