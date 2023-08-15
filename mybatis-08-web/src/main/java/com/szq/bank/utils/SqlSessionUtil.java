package com.szq.bank.utils;

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
    //全局的，服务器级别的，一个服务器当中定义一个即可
    private static  ThreadLocal<SqlSession> local=new ThreadLocal<>();
    /**
     * 获取会话对象
     * @return
     */
    public static SqlSession opeanSession(){
        SqlSession sqlSession=local.get();
        if (sqlSession == null) {
            sqlSession=sqlSessionFactory.openSession();
            //将sqlSession对象绑定到当前对象上
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭sqlSession对象(从当前线程中移除sqlSession对象)
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            //注意移除SqlSession对象和当前线程的绑定关系
            //因为Tomcat服务器支持线程池
            local.remove();
        }
    }

}
