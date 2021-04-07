package com.dunshan.mall;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.dunshan.mall.model.UmsMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: jdc测试
 * @author: 李文
 * @create: 2020-12-05 20:30
 **/
public class JdbcDomeTest001 {
    //起始id
    private static long begin = 1;
    //每次循环插入的数据量
    private static long end = begin + 10000;
    private static String url = "jdbc:mysql://172.16.106.130:30336/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
    private static String user = "reader";
    private static String password = "123456";
    private static String driverClass = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                getUserRegistration();
            }
        });


//        JdbcDomeTest001.insertBigData();
//        insertreg();
    }

    /**
     * 用户地址
     */
    public static void insertBigData() {
        //起始id
        long begin = 20037;
        //每次循环插入的数据量
        long end = begin + 10000;
        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName(driverClass);
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            // conn.setAutoCommit(false);
            //编写sql
            String sql = "INSERT INTO ums_member_receive_address VALUES (?,?,?,?,?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环10次，每次1万数据，一共1000万
            for (int i = 0; i < 10; i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    pstm.setLong(1, begin);
                    pstm.setLong(2, begin);
                    pstm.setString(3, RandomUtil.randomString(5));
                    pstm.setString(4, TestDome.getphone());
                    pstm.setInt(5, 0);
                    pstm.setString(6, RandomUtil.randomNumbers(5));
                    pstm.setString(7, "北京");
                    pstm.setString(8, "7dGruop性能实战");
                    pstm.setString(9, "7dGruop性能实战区");
                    pstm.setString(10, "吉地12号");
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
//                //提交事务
//                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时：" + (eTime1 - bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 用户注册
     */
    public static void getUserRegistration() {
        long begin = 1;
        //每次循环插入的数据量
        long end = begin + 1000;
        FileReader fileReader = new FileReader("/Users/liwen/IdeaProjects/7d/mall-swarm-new/document/shenfnz.csv");
        String result = fileReader.readString();
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName(driverClass);
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            // conn.setAutoCommit(false);
            //编写sql
            String sql = "insert into ums_member (member_level_id, username, password,nickname, phone, status,create_time, icon, gender,birthday, city, job, personalized_signature,source_type, integration, growth,luckey_count, history_integration) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //?表示一个参数的占位符
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环10次，每次1万数据，一共1000万
            for (int i = 0; i < 20; i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
                    String[] li = result.split("\n");
//                    pstm.setLong(1, begin);
                    //添加到同一个批处理中
                    UmsMember umsMember = new UmsMember();
                    umsMember.setId(begin);
                    umsMember.setUsername("7dtest" +RandomUtil.randomStringUpper(5) + getphone());
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
                    umsMember.setPersonalizedSignature("test001" + RandomUtil.randomStringUpper(5) + getRandom(1, 100));
                    umsMember.setGrowth(getRandom(1, 10));
                    umsMember.setLuckeyCount(getRandom(1, 99));
                    umsMember.setMemberLevelId(4L);
                    umsMember.setSourceType(getRandom(1, 10));
                    umsMember.setHistoryIntegration(getRandom(1, 10));
                    pstm.setString(1, String.valueOf(umsMember.getMemberLevelId()));
                    pstm.setString(2, umsMember.getUsername());
                    pstm.setString(3, umsMember.getPassword());
                    pstm.setString(4, umsMember.getNickname());
                    pstm.setString(5, umsMember.getPhone());
                    pstm.setInt(6, umsMember.getStatus());
                    Date utilDate = new Date();
                    pstm.setDate(7, new java.sql.Date(utilDate.getTime()));
                    pstm.setString(8, umsMember.getIcon());
                    pstm.setInt(9, umsMember.getGender());
                    pstm.setDate(10, new java.sql.Date(utilDate.getTime()));
                    pstm.setString(11, umsMember.getCity());
                    pstm.setString(12, umsMember.getJob());
                    pstm.setString(13, umsMember.getPersonalizedSignature());
                    pstm.setInt(14, umsMember.getSourceType());
                    pstm.setInt(15, umsMember.getIntegration());
                    pstm.setInt(16, umsMember.getGrowth());
                    pstm.setInt(17, umsMember.getLuckeyCount());
                    pstm.setInt(18, umsMember.getHistoryIntegration());
                    pstm.addBatch();
//                    System.out.println("第一批数据；"+begin);
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
//                //提交事务
//                conn.commit();
                //边界值自增1W，如果需要1千万下面改成10万
                end += 1000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入2W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时：" + (eTime1 - bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void insertreg() {
        long begin = 399356;
        //每次循环插入的数据量
        long end = begin + 10000;
        //定义连接、statement对象
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName(driverClass);
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            // conn.setAutoCommit(false);
            //编写sql
            //?表示一个参数的占位符
            String sql = "insert into ums_member (member_level_id, username, password,nickname, phone, status) values(?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环200次，每次1万数据，一共200万
            for (int i = 0; i < 200; i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //赋值
//                    pstm.setLong(1, begin);
                    pstm.setLong(1, 4);
                    pstm.setString(2, RandomUtil.randomStringUpper(5) + getphone());
                    pstm.setString(3, BCrypt.hashpw("123456"));
                    pstm.setString(4, RandomUtil.randomNumbers(5));
                    pstm.setString(5, TestDome.getphone());
                    pstm.setInt(6, 0);
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
//                //提交事务
//                conn.commit();
                //边界值自增1W
                end += 10;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时：" + (eTime1 - bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
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

}

