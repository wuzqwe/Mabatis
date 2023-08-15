package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Clazz;

public interface ClazzMapper {

    /**
     * 分步第二步：根据id获取班级信息
     * */
    Clazz selectById(Integer cid);


    /**
     * 根据班级id查询班级信息
     * @param cid
     * @return
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分步查询。第一步根据班级获取班级信息
     * @param cid 班级编号
     * @return
     */
    Clazz selectByStep1(Integer cid);
}
