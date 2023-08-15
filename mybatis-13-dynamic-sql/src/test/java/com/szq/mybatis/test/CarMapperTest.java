package com.szq.mybatis.test;


import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarMapperTest {

    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars =mapper.selectByMultiCondition("比亚迪",2.0,"电车");
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
//        List<Car> cars =mapper.selectByMultiConditionWithWhere("比亚迪",2.0,"电车");
        //三个条件都是空
//        List<Car> cars =mapper.selectByMultiConditionWithWhere("",null,"");
        //如果第一个条件为空
        List<Car> cars =mapper.selectByMultiConditionWithWhere("",2.0,"电车");
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars =mapper.selectByMultiConditionWithTrim("比亚迪",null,"");
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car=new Car(168L,null,"丰田",null,null,"燃油车");
        int count=mapper.updateById(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateBySet() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car=new Car(169L,null,"丰田",null,null,"燃油车");
        int count=mapper.updateBySet(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars=mapper.selectByChoose("丰田",null,null);
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids={193L,194L,195L};
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car1=new Car(null,"1200","psk",30.0,"2020-11-11","燃油车");
        Car car2=new Car(null,"1200","pssdfk",30.0,"2020-11-11","燃油车");
        Car car3=new Car(null,"1200","psdsk",30.0,"2020-11-11","燃油车");
        List<Car> list=new ArrayList<>();
        list.add(car1);
        list.add(car2);
        list.add(car3);
        int count = mapper.insertBatch(list);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds2() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids ={196L,197L,198L};
        int count=mapper.deleteByIds2(ids);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
