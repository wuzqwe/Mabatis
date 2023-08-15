package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    List<Car> selectAll();

    /**
     * 查询所有Car信息。使用resultMap标签进行结果映射
     * @return
     */
    List<Car> selectAllResultMap();

    List<Car> selectAllByMapUnderscoreToCamelCase();

    /**
     * 获取Car的总记录条数
     * @return
     */
    Long selectTotal();
}
