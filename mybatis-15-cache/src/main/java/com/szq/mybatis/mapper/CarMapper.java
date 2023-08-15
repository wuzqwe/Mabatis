package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {
    Car selectById(Long id);

    /**
     * 测试二级缓存
     * @param id
     * @return
     */
    Car selectById2(Long id);
}
