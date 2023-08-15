package com.szq.mybatis.test;

import com.szq.mybatis.core.SqlSession;
import com.szq.mybatis.core.SqlSessionFactory;
import com.szq.mybatis.core.SqlSessionFactoryBuilder;
import com.szq.mybatis.pojo.User;
import com.szq.mybatis.util.Resources;
import org.junit.Test;

public class GodBatisTest {
    @Test
    public void testSqlSessionFactory(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        System.out.println(build);
    }

    @Test
    public void testInsertUser(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = build.opeanSession();
        User user = new User("333","zhangsan","20");
        int count = sqlSession.insert("user.insertCar", user);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectOneUser(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlsessionfactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlsessionfactory.opeanSession();
        Object user = (User)sqlSession.selectOne("user.selectById", "333");
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
