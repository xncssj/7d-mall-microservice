package com.dunshan.mall;

import cn.hutool.crypto.digest.BCrypt;
import com.alibaba.fastjson.JSON;
import com.dunshan.mall.mapper.UmsMemberMapper;
import com.dunshan.mall.member.MallMemberApplication;
import com.dunshan.mall.model.UmsMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

/**
 * @description: 测试用户注册测试
 * @author: 李文
 * @create: 2020-12-03 22:22
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MallMemberApplication.class)
public class TestDemoLogin {
//
//    @Autowired
//    private UmsMemberMapper memberMapper;

    @Test
    public void getLoginTest() {
        Long statie = System.currentTimeMillis();
        oo1();
        Long endtime = System.currentTimeMillis();
        System.out.println("话费时间" + (endtime - statie));
    }

    public void oo1() {
        for (int i = 1; i < 2; i++) {
            Random random = new Random();
            UmsMember umsMember = new UmsMember();
            umsMember.setUsername("7dsTest1" + i);
            umsMember.setPhone(gethoo());
            umsMember.setPassword(BCrypt.hashpw("123456"));
            umsMember.setCreateTime(new Date());
            umsMember.setStatus(1);
            umsMember.setNickname("7dsTest" + i);
            umsMember.setIcon("https://perfo7d.oss-cn-beijing.aliyuncs.com/mall/images/20200923/1522738681.jpg");
            umsMember.setGender(random.nextInt(2)+1);
            umsMember.setBirthday(new Date());
            umsMember.setCity("北京" + i);
            umsMember.setJob("计算机-性能测试001" + i);
            umsMember.setPersonalizedSignature("挑战性能测试");
            umsMember.setGrowth(random.nextInt(4)+1);
            umsMember.setIntegration(random.nextInt(4)+1);
            umsMember.setSourceType(random.nextInt(4)+1);
            umsMember.setMemberLevelId(Long.valueOf(random.nextInt(4)+1));
            umsMember.setLuckeyCount(random.nextInt(10)+1);
            umsMember.setHistoryIntegration(random.nextInt(20)+1);
//            memberMapper.insert(umsMember);
//            System.out.println(JSON.toJSON(umsMember));

            String str1 = "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
            String str = String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'",
                    umsMember.getMemberLevelId(), umsMember.getUsername(),
                    umsMember.getPassword(),umsMember.getNickname(),
                    umsMember.getPhone(),umsMember.getStatus(),
                    umsMember.getCreateTime(),umsMember.getIcon(),
                    umsMember.getGender(), umsMember.getBirthday(),
                    umsMember.getCity(),umsMember.getJob(),
                    umsMember.getPersonalizedSignature(),umsMember.getSourceType(),
                    umsMember.getIntegration(),umsMember.getGrowth(),
                    umsMember.getLuckeyCount(), umsMember.getHistoryIntegration());
            System.out.println("("+str+")");

        }
    }


    public String gethoo() {
        Random random = new Random();
        String[] phone = {"133", "149", "153", "173", "177", "180", "181", "189", "191", "199", "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172", "178", "182", "183", "184", "187", "188", "198"};
        int first = random.nextInt(phone.length);
        StringBuilder stringBuilder = new StringBuilder(phone[first]);
        for (int j = 0; j < 8; j++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

}

