package com.szq.mybatis.util.test.junit.service;

/**
 * 数学业务类
 * @author 吾空白
 * @version 1.0
 * @since 1.0
 */
public class MathService {
    /**
     * 求和
     * @param a
     * @param b
     * @return
     */
    public int sum(int a,int b){
        return a+b;
    }

    /**
     * 相减
     * @param a
     * @param b
     * @return
     */
    public int sub(int a,int b){
        return a-b;
    }
}
