package com.dunshan.mall.member.repository;

import com.dunshan.mall.member.domain.MemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览历史Repository
 * Created by dunshan on 2018/8/3.
 * extends MongoRepository<MemberReadHistory,String>
 *     1
 */
public interface MemberReadHistoryRepository {
    Page<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId, Pageable pageable);
    void deleteAllByMemberId(Long memberId);

    void deleteAll(List<MemberReadHistory> deleteList);

    void save(MemberReadHistory memberReadHistory);
}
