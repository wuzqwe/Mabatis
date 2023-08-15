package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testDeleteByPrimaryKey() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteByPrimaryKey(168L);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
