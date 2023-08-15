package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {
    /**
     * 根据id获取学生信息。同时获取学生关联的班级信息
     * @param id 学生id
     * @return学生对象，学生对象含有班级对象
     */
     Student selectById(Integer id);


    /**
     * 一条Sql语句，association
     * @param id
     * @return
     */
    Student selectByIdAssociation(Integer id);


    /**
     * 分步查询第一步：先根据学生的sid查询学生的信息
     * @param id
     * @return
     */
    Student selectByIdStep1(Integer id);

    /**
     *根据班级编号查询学生信息
     * @param cid
     * @return
     */
    List<Student> selectByCidStep2(Integer cid);
}
