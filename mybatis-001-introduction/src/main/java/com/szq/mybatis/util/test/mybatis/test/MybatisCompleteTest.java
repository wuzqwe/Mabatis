package com.szq.mybatis.util.test.mybatis.test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * 采用正规的方式，写一个完整版的Mybatis程序
 * @author 吾空白
 * @version 1.0
 * @since 1.0
 */
public class MybatisCompleteTest {
    public static void main(String[] args) {
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
}
