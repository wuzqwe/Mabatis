package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {


    /**
     * 根据id批量删除，使用or关键字
     * @param ids
     * @return
     */
    int deleteByIds2(@Param("ids") Long[] ids);
    /**
     * 批量插入，一次插入多条Car信息
     * @param cars
     * @return
     */
    int insertBatch(@Param("cars") List<Car> cars);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Long[] ids);
    /**
     * 多条件查询
     * @param brand 品牌
     * @param guidePrice 指导价
     * @param carType 汽车类型
     * @return
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand,@Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 使用where语句
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand,@Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 使用trim标签
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand,@Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    int updateById(Car car);

    int updateBySet(Car car);

    /**
     * 使用choose when otherwise标签
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand,@Param("guidePrice") Double guidePrice, @Param("carType") String carType);
}
