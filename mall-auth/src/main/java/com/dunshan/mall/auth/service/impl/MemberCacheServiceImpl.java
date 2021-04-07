package com.dunshan.mall.auth.service.impl;

import com.dunshan.mall.auth.service.MemberCacheService;
import com.dunshan.mall.common.annotation.CacheException;
import com.dunshan.mall.common.service.RedisService;
import com.dunshan.mall.model.UmsMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: 用户缓存业务实现类
 * @author: 李文
 * @create: 2020-11-15 14:19
 **/
@Service
public class MemberCacheServiceImpl implements MemberCacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;

    @Override
    public void delMember(Long memberId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + memberId;
        redisService.del(key);
    }

    @Override
    public UmsMember getMember(Long memberId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + memberId;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public UmsMember getMemberByUsername(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":username:" + username;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public void setMember(UmsMember member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getId();
        redisService.set(key, member, REDIS_EXPIRE);

        key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":username:" + member.getUsername();
        redisService.set(key, member, REDIS_EXPIRE);
    }

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }
}

