package com.szq.mybatis.util.test;

import com.szq.mybatis.util.pojo.Car;
import com.szq.mybatis.util.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMapperTest {

    @Test
    public void testInsertCar(){
        SqlSession session = SqlSessionUtil.opeanSession();
        //insert方法的参数
        //第一个参数
        //第二个参数：封装数据的对象

        Map<String,Object> map=new HashMap<>();
        /*map.put("k1","1111");
        map.put("k2","比亚迪");
        map.put("k3",10.0);
        map.put("k4","2020-19-11");
        map.put("k5","电车");*/

        map.put("carNum", "1111");
        map.put("brand", "比亚迪汉2");
        map.put("guidePrice", 10.0);
        map.put("produceTime", "2020-11-11");
        map.put("carType", "电车");

        int count = session.insert("insertCar",map);
        System.out.println(count);
        session.commit();
        session.close();

    }

    @Test
    public void testInsertCarPojo(){
        SqlSession session = SqlSessionUtil.opeanSession();
        Car car = new Car(null,"3333","崩牙驹",30.0,"2020-12-11","电车");

        session.insert("insertCar",car);
        session.commit();
        session.close();
    }

    @Test
    public void testDeteById(){
        SqlSession session = SqlSessionUtil.opeanSession();
        int count = session.delete("deleteById", 180);
        System.out.println(count);
        session.commit();
        session.close();
    }

    @Test
    public void testUpdateById(){
        SqlSession session = SqlSessionUtil.opeanSession();
        Car car=new Car(164L,"9999","凯官邸",30.3,"2999-12-23","电车");
        int count = session.update("updateById", car);
        System.out.println(count);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectById(){
        SqlSession session = SqlSessionUtil.opeanSession();
        Object car = session.selectOne("selectById", 164);
        System.out.println(car);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession session = SqlSessionUtil.opeanSession();
        List<Car> cars = session.selectList("aaa.selectAll");
        cars.forEach(car -> System.out.println(car));
        session.commit();
        session.close();
    }

    @Test
    public void testNameSpace(){
        SqlSession session = SqlSessionUtil.opeanSession();
        List<Car> cars = session.selectList("aaa.selectAll");
        cars.forEach(car -> System.out.println(car));
        session.commit();
        session.close();
    }

}
