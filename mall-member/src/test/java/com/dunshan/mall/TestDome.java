package com.dunshan.mall;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.alibaba.fastjson.JSON;
import com.dunshan.mall.mapper.UmsMemberMapper;
import com.dunshan.mall.member.utils.JdbcUtil;
import com.dunshan.mall.model.UmsMember;
import com.dunshan.mall.model.UmsMemberReceiveAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-12-03 20:52
 * @Description: 调试
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestDome {

    @Autowired
    private UmsMemberMapper memberMapper;


    //    Java代码 复制代码  收藏代码
    @Test
    public void exec() {
        for (int j = 0; j < 3; j++) {
            ArrayList<UmsMember> list = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                UmsMember umsMember = new UmsMember();
                umsMember.setId(null);
                umsMember.setUsername("7dtest" + getphone());
                umsMember.setPhone(getphone());
                umsMember.setNickname("性能实战001" + i + j);
                umsMember.setPassword(BCrypt.hashpw("123456"));
                umsMember.setCreateTime(new Date());
                umsMember.setStatus(1);
                umsMember.setBirthday(new Date());
                umsMember.setIcon("https://perfo7d.oss-cn-beijing.aliyuncs.com/mall/images/20200923/web.png");
                umsMember.setGender(getRandom(0, 2));
                umsMember.setBirthday(new Date());
                umsMember.setCity("北京" + j);
                umsMember.setJob("test");
                umsMember.setPersonalizedSignature("test001" + getRandom(1, 100));
                umsMember.setGrowth(getRandom(1, 10));
                umsMember.setLuckeyCount(getRandom(1, 99));
                umsMember.setMemberLevelId(4L);
                umsMember.setIntegration(getRandom(1, 99));
                umsMember.setSourceType(getRandom(1, 10));
                umsMember.setHistoryIntegration(getRandom(1, 10));
//                System.out.println(umsMember);
//                memberMapper.insert(umsMember);
                list.add(umsMember);
            }
//            System.out.println(JSON.toJSON(list));
            memberMapper.insertUmsMember(list);
        }
    }

    public static String getphone() {
        String[] phone = {"133", "149", "153", "173", "177", "180", "181", "189", "191", "199", "130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172", "178", "182", "183", "184", "187", "188", "198"};
        Random random = new Random();
        int first = random.nextInt(phone.length);
        StringBuilder stringBuilder = new StringBuilder(phone[first]);
        for (int i = 0; i < 8; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public static int getRandom(int min, int max) {
        Random random = new Random();
        int i = random.nextInt(max) % (max - min + 1) + min;
        return i;
    }


    @Test
    public void getlistum() throws SQLException {
//        List<UmsMember> umsMembers = memberMapper.selectByExample(null);
//        System.out.println(umsMembers);


        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/7dnewmall?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true", "root", "jd123root");
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement(
                "insert into ums_member (member_level_id, username, password,nickname, phone, status,create_time, icon, gender,birthday, city, job, personalized_signature,source_type, integration, growth,luckey_count, history_integration) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        UmsMember umsMember = new UmsMember();
        umsMember.setId(null);
        umsMember.setUsername("7dtest" + getphone());
        umsMember.setPhone(getphone());
        umsMember.setNickname("性能实战001");
        umsMember.setPassword(BCrypt.hashpw("123456"));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        umsMember.setBirthday(new Date());
        umsMember.setIcon("https://perfo7d.oss-cn-beijing.aliyuncs.com/mall/images/20200923/web.png");
        umsMember.setGender(getRandom(0, 2));
        umsMember.setBirthday(new Date());
        umsMember.setCity("北京");
        umsMember.setJob("test");
        umsMember.setPersonalizedSignature("test001" + getRandom(1, 100));
        umsMember.setGrowth(getRandom(1, 10));
        umsMember.setLuckeyCount(getRandom(1, 99));
        umsMember.setMemberLevelId(4L);
        umsMember.setSourceType(getRandom(1, 10));
        umsMember.setHistoryIntegration(getRandom(1, 10));
        ps.setString(1, String.valueOf(umsMember.getMemberLevelId()));
        ps.setString(2, umsMember.getUsername());
        ps.setString(3, umsMember.getPassword());
        ps.setString(4, umsMember.getNickname());
        ps.setString(5, umsMember.getPhone());
        ps.setInt(6, umsMember.getStatus());
        java.util.Date utilDate = new java.util.Date();
        ps.setDate(7, new java.sql.Date(utilDate.getTime()));
        ps.setString(8, umsMember.getIcon());
        ps.setInt(9, umsMember.getGender());
        ps.setDate(10, new java.sql.Date(utilDate.getTime()));
        ps.setString(11, umsMember.getCity());
        ps.setString(12, umsMember.getJob());
        ps.setString(13, umsMember.getPersonalizedSignature());
        ps.setInt(14, umsMember.getSourceType());
        ps.setInt(15, umsMember.getIntegration());
        ps.setInt(16, umsMember.getGrowth());
        ps.setInt(17, umsMember.getLuckeyCount());
        ps.setInt(18, umsMember.getHistoryIntegration());
        ps.addBatch();
        ps.executeBatch();
        connection.commit();
    }


    /**
     * (invocationCount = 10, threadPoolSize = 3)
     * 增加
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(30);

        System.out.println("开始。。。");
//        getOnehter(String.valueOf(getRandom(1, 100)));
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getOnehter("q");
//            }
//        });
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getOnehter("w");
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getOnehter("r");
//            }
//        });
//        thread.start();
//        thread1.start();
//        thread2.start();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cim");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cmia");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cpbi");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cpic");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cmpdi");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getOnehter("cppei");
            }
        });
    }

    public static void getOnehter(String strN) {
        //1.获取连接
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JdbcUtil.getConnection();
        FileReader fileReader = new FileReader("/Users/liwen/IdeaProjects/7d/mall-swarm-new/document/shenfnz.csv");
        String result = fileReader.readString();
        Long startTime = System.currentTimeMillis();

        try {
            for (int k = 0; k < 100000; k++) {
                String[] li = result.split("\n");
                //2.准备预编译的sql
                String sql = "insert into ums_member (member_level_id, username, password,nickname, phone, status,create_time, icon, gender,birthday, city, job, personalized_signature,source_type, integration, growth,luckey_count, history_integration) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //?表示一个参数的占位符
                //3.执行预编译sql语句(检查语法)
                ps = conn.prepareStatement(sql);
                //4.设置参数值
                /**
                 * 参数一： 参数位置  从1开始
                 */
                UmsMember umsMember = new UmsMember();
                umsMember.setId(null);
                umsMember.setUsername("7d" + strN + "test" + getphone());
                umsMember.setPhone(getphone());
                umsMember.setNickname("性能实战001");
                umsMember.setPassword(BCrypt.hashpw("123456"));
                umsMember.setCreateTime(new Date());
                umsMember.setStatus(1);
                umsMember.setBirthday(new Date());
                umsMember.setIcon("https://perfo7d.oss-cn-beijing.aliyuncs.com/mall/images/20200923/web.png");
                umsMember.setGender(getRandom(0, 2));
                umsMember.setBirthday(new Date());
                umsMember.setCity(li[getRandom(0, li.length)]);
                umsMember.setJob("test");
                umsMember.setIntegration(getRandom(1, 99));
                umsMember.setPersonalizedSignature("test001" + getRandom(1, 100));
                umsMember.setGrowth(getRandom(1, 10));
                umsMember.setLuckeyCount(getRandom(1, 99));
                umsMember.setMemberLevelId(4L);
                umsMember.setSourceType(getRandom(1, 10));
                umsMember.setHistoryIntegration(getRandom(1, 10));
                ps.setString(1, String.valueOf(umsMember.getMemberLevelId()));
                ps.setString(2, umsMember.getUsername());
                ps.setString(3, umsMember.getPassword());
                ps.setString(4, umsMember.getNickname());
                ps.setString(5, umsMember.getPhone());
                ps.setInt(6, umsMember.getStatus());
                java.util.Date utilDate = new java.util.Date();
                ps.setDate(7, new java.sql.Date(utilDate.getTime()));
                ps.setString(8, umsMember.getIcon());
                ps.setInt(9, umsMember.getGender());
                ps.setDate(10, new java.sql.Date(utilDate.getTime()));
                ps.setString(11, umsMember.getCity());
                ps.setString(12, umsMember.getJob());
                ps.setString(13, umsMember.getPersonalizedSignature());
                ps.setInt(14, umsMember.getSourceType());
                ps.setInt(15, umsMember.getIntegration());
                ps.setInt(16, umsMember.getGrowth());
                ps.setInt(17, umsMember.getLuckeyCount());
                ps.setInt(18, umsMember.getHistoryIntegration());
                //5.发送参数，执行sql
                System.out.println("执行的是：" + ps);
                int count = ps.executeUpdate();
                System.out.println("影响了" + count + "行，-》" + k);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps);
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("执行多少时间" + (endTime - startTime));
    }


    public void getAdderss() {


    }

    @Test
    public void getscvec() {
        FileWriter writer = new FileWriter("/Users/liwen406/IdeaProjects/Performance/mall-swarm-new/document/addre.txt");
        FileReader fileReader = new FileReader("/Users/liwen406/IdeaProjects/Performance/mall-swarm-new/document/shenfnz.csv");
        String result = fileReader.readString();
        String[] resul = result.split("\n");
        for (String s : resul) {
            int i = s.indexOf("\"");
            String sub1 = s.substring(0, i);
            String sub2 = s.substring(i);
            String[] res = sub1.split(",");
            UmsMemberReceiveAddress address = new UmsMemberReceiveAddress();
            address.setMemberId(241L);
            address.setName("7dtest_" + getphone());
            address.setPhoneNumber(getphone());
            address.setDefaultStatus(0);
            address.setPostCode(RandomUtil.randomNumbers(6));
            //省份/直辖市
            address.setProvince(res[0]);
            //城市
            address.setCity(res[0]);
            //区
            address.setRegion(res[1]);
            address.setDetailAddress(sub2 + "中山街23号");
//            System.out.println("结果：" + JSON.toJSON(address));
            writer.append(JSON.toJSON(address) + "\n");
        }
    }


}
