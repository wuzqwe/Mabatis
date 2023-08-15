package com.szq.mybatis.test;

import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.pojo.CarExample;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CarMapperTest {

    //CarExample类负责封装查询条件的
    @Test
    public void testSelect() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //执行查询
        //1.查询一个
        Car car = mapper.selectByPrimaryKey(169L);
        System.out.println(car);
        //2.查询所有(selectByExample 根据条件查询，如果条件是null表示没有条件)
        List<Car> cars=mapper.selectByExample(null);
        cars.forEach(car1 -> {
            System.out.println(car1);
        });
        System.out.println("===================================");
        //3.按照条件查询
        //CarExample类负责封装查询条件的
        CarExample carExample=new CarExample();
        //QBC风格 比较面向对象 看不到sql语句
        //carExample.createCriteria()创造查询条件
        carExample.createCriteria().andBrandLike("比亚迪")
                        .andGuidePriceGreaterThan(new BigDecimal(20.0));
        carExample.or().andCarTypeEqualTo("燃油车");
        //执行查询
        List<Car> cars1=mapper.selectByExample(carExample);
        cars1.forEach(car2 -> {
            System.out.println(car2);
        });
        sqlSession.close();
    }
}
