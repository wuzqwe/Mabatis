package com.szq.mybatis.util.test.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 */
public class MathServiceTest {//名字规范：你要测试的类名+Test
    //单元测试方法写多少个
    //一般是一个业务方法对应一个测试方法
    //测试方法的规范：public void testXxxx(){}
    //测试方法的类名：以test开始。假设测试的方法是sum，这个测试方法名：testSum
    @Test
    public void testSum(){
        //单元测试有俩个重要的概念
        //一个是：实际值（被测试的业务的真正执行结束）
        //一个是：期望值（执行了这个业务方法之后，你的期望的执行结果是多少）
        MathService mathService=new MathService();
        //获取实际值
        int sub = mathService.sum(1, 2);
        //获取期望值
        int expexted=3;
        //加断言测试
        Assert.assertEquals(expexted,sub);


    }

    @Test
    public void testSub(){
        //单元测试有俩个重要的概念
        //一个是：实际值（被测试的业务的真正执行结束）
        //一个是：期望值（执行了这个业务方法之后，你的期望的执行结果是多少）
        MathService mathService=new MathService();
        //获取实际值
        int sub = mathService.sub(1, 2);
        //获取期望值
        int expexted=-1;
        //加断言测试
        Assert.assertEquals(expexted,sub);
    }
}
