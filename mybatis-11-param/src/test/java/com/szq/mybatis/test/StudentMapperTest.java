package com.szq.mybatis.test;

import com.szq.mybatis.mapper.StudentMapper;
import com.szq.mybatis.pojo.Student;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(student -> {
            System.out.println(student);
        });

    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByName("张三");
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void testSelectByBirht() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = sdf.parse("1980-10-11");
        List<Student> students = mapper.selectByBirth(birth);
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void testSelectBySex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectBySex('男');
        students.forEach(student -> {
            System.out.println(student);
        });

    }

    @Test
    public void testInsertStudentByMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "sd");
        map.put("age", 20);
        map.put("height", 1.80);
        map.put("sex", '男');
        map.put("birth", new Date());
        int count = mapper.insertStudentByMap(map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertStudentPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student(null, "zhas", 20, 1.89, new Date(), '男');
        int count = mapper.insertStudentPOJO(student);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex("张三", '男');
        students.forEach(student -> {
            System.out.println(student);
        });
    }

    @Test
    public void testInsertByNameAndSex2() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByNameAndSex2("张三", '男');
        students.forEach(student -> {
            System.out.println(student);
        });
    }
}
