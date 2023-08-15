package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        System.out.println(cars);
        sqlSession.close();
    }

    @Test
    public void testSelectAllResultMap() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllResultMap();
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectAllByMapUnderscoreToCamelCase() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByMapUnderscoreToCamelCase();
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectTotal() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long total = mapper.selectTotal();
        System.out.println(total);
    }
}
