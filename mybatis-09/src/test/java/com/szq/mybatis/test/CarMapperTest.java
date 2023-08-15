package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null,"6865","宝马",3.0,"2000-10-10","新能源");
        int count = mapper.insert(car);
        System.out.println(count);
        sqlSession.commit();
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteById(164L);
        System.out.println(count);
        sqlSession.commit();
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(192L,"222","宝dfsr马",3.0,"2000-10-10","新能源");
        int count = mapper.update(car);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(165L);
        System.out.println(car);
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(car -> {
            System.out.println(car);
        });
    }
}
