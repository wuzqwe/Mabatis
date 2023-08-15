package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(192L);
        System.out.println(car);
        //手动清空一级缓存
        sqlSession.clearCache();
        Car car1 = mapper.selectById(192L);
        System.out.println(car1);
        sqlSession.close();
    }

    /*
    * 什么时候不走缓存’
    * SqlSession对象不是同一个，肯定不走缓存
    * 查询条件不一样，肯定也不走缓存。
    *
    * 什么时候一级缓存失效
    * 第一次DQl和第二次
    * 第一次DQl和第二次DQL之间你做了以下俩件事中的任意一件，都会让一级缓存清空
    * 1.执行了sqlSession的clearCache()方法,这是手动清空缓存
    * 2.执行了Insert或delete或update语句。不管操作那张表的，都会清空一级缓存
    * */
    @Test
    public void testSelectById1() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = build.openSession();
        SqlSession sqlSession1 = build.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(192L);
        System.out.println(car);

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        Car car1 = mapper1.selectById(192L);
        System.out.println(car1);

        sqlSession.close();
        sqlSession1.close();

    }

    @Test
    public void testSelectId2() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);

        /*如果这里不关闭SqlSession会话对象，会缓存到一级缓存中*/
        Car car = mapper.selectById2(192L);
        System.out.println(car);
        sqlSession.close();

        Car car1= mapper1.selectById2(192L);
        System.out.println(car1);

        //程序执行到这里后，会将SqlSession一级缓存的数据写入二级缓存中

        sqlSession1.close();

    }
}
