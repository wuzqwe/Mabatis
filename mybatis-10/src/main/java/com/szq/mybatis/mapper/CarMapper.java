package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 批量删除，根据id
     * @param ids
     * @return
     */
    int deleteBatch(String ids);

    /**
     * 根据汽车品牌进行模糊查询
     * @param brand
     * @return
     */
    List<Car> seleteByBrandLike(String brand);

    /**
     * 插入Car信息，并且使用生成的主键值
     * @param car
     * @return
     */
    int insertCarUserGeneratedKeys(Car car);

}
