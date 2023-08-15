package com.szq.mybatis.mapper;

import com.szq.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentMapper {
/**
 * 当接口中的方法的参数只有一个（单个参数），并且参数的数据类型都是简单类型
 * 根据id查询、name查询、birth查询、sex查询
 */
    List<Student> selectById(Long id);
    List<Student> selectByName(String name);
    List<Student> selectByBirth(Date birth);
    List<Student> selectBySex(Character sex);

    /**
     * 保存学生信息，通过Map参数
     * @param map
     * @return
     */
    int insertStudentByMap(Map<String,Object> map);

    /**
     * 根据对象插入
     * @param student
     * @return
     */
    int insertStudentPOJO(Student student);

    /**
     * 根据名字和姓名查询
     * 如果是多个参数的话，mybatis框架底层是怎么做的
     * mybatis框架会自动创建一个Map集合，并且Map集合是以这种方式存在
     * map.put("arg0",name);
     * map.put("arg1",sex);
     * map.put("param1",name);
     *  map.put("param2",name);
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSex(String name,Character sex);

    /**
     * param注解
     * mybatis框架底层原理
     * map.put("name",name);
     * map.put("sex",sex);
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSex2(@Param("name") String name, @Param("sex") Character sex);
}
