package com.szq.mybatis.util.test;

import com.szq.mybatis.util.test.mybatis.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

public class CarMapperTest {
    @Test
    public void testInsertCar(){
        //编写mybatis程序
        SqlSession session=null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory build = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis.config.xml"));
            session = build.openSession();
            //执行sql语句，处理相关业务
            int count = session.insert("insertCar");
            System.out.println(count);
            //执行到这里，没有发生任何异常，提交事务
            session.commit();
        } catch (IOException e) {
            //遇到异常回滚事务
            if (session!=null)
            {
                session.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Test
    public void testInsertUtil(){
        SqlSession session = SqlSessionUtil.opeanSession();
        int count = session.insert("insertCar");
        System.out.println(count);
        session.commit();
        session.close();
    }
}
