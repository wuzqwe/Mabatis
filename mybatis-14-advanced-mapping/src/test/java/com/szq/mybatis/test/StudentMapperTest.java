package com.szq.mybatis.test;

import com.szq.mybatis.mapper.StudentMapper;
import com.szq.mybatis.pojo.Student;
import com.szq.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentMapperTest {
    @Test
    public void testsSelectById() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
//        System.out.println(student.getSid());
//        System.out.println(student.getSname());
//        System.out.println(student.getClazz().getCid());
//        System.out.println(student.getClazz().getCname());
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdAssociation() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAssociation(4);
        System.out.println(student);
        sqlSession.close();

    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.opeanSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student=mapper.selectByIdStep1(5);
//        System.out.println(student);
        System.out.println(student.getSname());

        System.out.println(student.getClazz().getCname());
        sqlSession.close();
    }
}
