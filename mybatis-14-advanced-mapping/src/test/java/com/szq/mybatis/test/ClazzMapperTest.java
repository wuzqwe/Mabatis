package com.szq.mybatis.test;

import com.szq.mybatis.mapper.ClazzMapper;
import com.szq.mybatis.pojo.Clazz;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ClazzMapperTest {

    @Test
    public void testSelectByCollection() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1000);
        System.out.println(clazz);
        sqlSession.close();
    }

    @Test
    public void testSelectByCidStep2() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1000);
//        System.out.println(clazz);
        System.out.println(clazz.getCname());
        sqlSession.close();
    }
}
