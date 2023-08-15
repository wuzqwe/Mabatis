package com.szq.mybatis.util;

import java.io.InputStream;

/**
 * godbatis框架提供的一个工具类
 * 这个工具类专门完成“类路径中资源的加载
 */
public class Resources {
/*
* 构造方法私有化
* 工具类的构造方法都是私有化，防止new对象
* 这只是一种编程的习惯
* */
    private Resources(){

    }

    /**
     * 从类路径当中加载资源
     * @param resource 放在类路径当中的资源文件
     * @return 指向资源文件的一个输入流
     */
    public static InputStream getResourceAsStream(String resource){
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }
}
