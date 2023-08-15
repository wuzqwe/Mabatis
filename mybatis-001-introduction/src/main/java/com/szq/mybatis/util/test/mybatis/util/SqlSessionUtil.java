package com.szq.mybatis.util.test.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Mybatis工具类
 */
public class SqlSessionUtil {
    //工具类的构造方法一般都是私有化的
    //工具类所有方法都是静态的
    //为了防止new对象

    private static SqlSessionFactory sqlSessionFactory;


    private SqlSessionUtil() {
    }

    /*public static SqlSession opeanSession(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //SqlSessionFactory对象：一个SqlSessionFactory对应一个environment,一个environment对应一个数据库
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis.config.xml"));
        SqlSession session = build.openSession();
        return session;
    }*/

    //类加载时执行
    //SqlSessionUtil工具类在进行第一次类加载的时候解析mybatis-config.xml文件，新建SqlSessionFactory
    static {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            sqlSessionFactory= sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis.config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话对象
     * @return
     */
    public static SqlSession opeanSession(){
        return sqlSessionFactory.openSession();
    }

}
