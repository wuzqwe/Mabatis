package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {

    @Test
    public void testDeleteBatch(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count=mapper.deleteBatch("165,166,167");
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectBrand(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars=mapper.seleteByBrandLike("奥迪");
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car=new Car(null,"999","开热",30.0,"2022-11-11","燃油车");
        mapper.insertCarUserGeneratedKeys(car);
        System.out.println(car);
        sqlSession.commit();
        sqlSession.close();
    }
}
