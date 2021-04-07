package com.dunshan.mall.member.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author LiWen
 * @version 1.0
 * @Date: 2020-12-04 12:50
 * @Description: jdc工具
 */
public class JdbcUtil {
    private static String url = "jdbc:mysql://localhost:3306/mall_new?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
    private static String user = "root";
    private static String password = "jd123root";
    private static String driverClass = "com.mysql.cj.jdbc.Driver";

    /**
     * 静态代码块中（只加载一次）
     */
    static {
        try {
            //读取db.properties文件
            Properties props = new Properties();
            /**
             *  . 代表java命令运行的目录
             *  在java项目下，. java命令的运行目录从项目的根目录开始
             *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
             *  所以不能使用点.
             */
            //FileInputStream in = new FileInputStream("./src/db.properties");

            /**
             * 使用类路径的读取方式
             *  / : 斜杠表示classpath的根目录
             *     在java项目下，classpath的根目录从bin目录开始
             *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
             */
//            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");
//
//            //加载文件
//            props.load(in);
//            //读取信息
//            url = props.getProperty("url");
//            user = props.getProperty("user");
//            password = props.getProperty("password");
//            driverClass = props.getProperty("driverClass");


            //注册驱动程序
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱程程序注册出错");
        }
    }

    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 释放资源的方法
     */
    public static void close(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
