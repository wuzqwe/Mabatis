package com.szq.mybatis.util.test.mybatis.test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class MyBatisIntroductionTest {
    public static void main(String[] args) throws Exception{
        //获取SqlSessionFactoryBuild对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory对象
        InputStream inputStream= Resources.getResourceAsStream("mybatis.config.xml");
        //底层调用的就是ClassLoader.getSystemClassLoader()方法
//        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis.config.xml");
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(inputStream);
        //获取SqlSession对象
        SqlSession session = build.openSession();

        //执行Sql语句
        int count = session.insert("insertCar");//返回值是影响数据库表当中的记录条数

        System.out.println("插入了几条记录："+count);

        //手动提交
        session.commit();

    }
}
