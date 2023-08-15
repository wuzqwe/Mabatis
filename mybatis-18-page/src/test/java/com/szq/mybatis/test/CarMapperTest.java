package com.szq.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szq.mybatis.mapper.CarMapper;
import com.szq.mybatis.pojo.Car;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByPage() {
        //显示第几页
        int pageNum=1;
        //显示记录条数
        int pageSize=3;
        //计算开始下标
        int startIndex=(pageNum-1)*pageSize;

        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car>  cars=mapper.selectByPage(startIndex,pageSize);
        cars.forEach(car -> {
            System.out.println(car);
        });
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        //一定要注意，在执行DQL语句之前，开启分页功能
        //显示第几页
        int pageNum=2;
        //显示记录条数
        int pageSize=3;
        PageHelper.startPage(pageNum,pageSize);
        List<Car> cars = mapper.selectAll();
       /* cars.forEach(car -> {
            System.out.println(car);
        });*/
        //封装分页信息对象new PageInfo
        //PageInfo对象是PageHelper插件提供的，用来封装相关的信息对象
        PageInfo<Car> carPageInfo=new PageInfo<>(cars,3);
        System.out.println(carPageInfo);
    }
}
