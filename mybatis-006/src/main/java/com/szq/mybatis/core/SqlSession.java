package com.szq.mybatis.core;

import java.lang.reflect.Method;
import java.sql.*;

/**
 * 专门负责执行SQL语句的会话对象
 */
public class SqlSession {
    private SqlSessionFactory factory;

    public SqlSession(SqlSessionFactory factory) {
        this.factory = factory;
    }

    //insert

    /**
     * 执行sql语句，向数据库表当中插入记录
     * @param sqlId sql语句的id
     * @param pojo 插入的数据。ORM
     */
    public int insert(String sqlId,Object pojo){
       int count=0;
        try {
            //JDBC代码，执行insert语句。完成插入操作
            Connection connection = factory.getTransaction().getConnection();
            String godbatissql=factory.getMappedStatementMap().get(sqlId).getSql();
            String sql=godbatissql.replaceAll("#\\{[a-zA-Z0-9_$]*}","?");
            PreparedStatement ps = connection.prepareStatement(sql);
            //给占位符传值
            int formIndex=0;
            int index=1;
            while (true){
                int jingIndex=godbatissql.indexOf("#",formIndex);
                if (jingIndex<0){
                    break;
                }
                int youKuoHaoIndex=godbatissql.indexOf("}",formIndex);
                String propertyName=godbatissql.substring(jingIndex+2,youKuoHaoIndex).trim();
                formIndex=youKuoHaoIndex+1;
                //有属性方法id,怎么获取id的属性值呢?调用getId()方法
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                Object propertyValue = getMethod.invoke(pojo);
                ps.setString(index,propertyValue.toString());
                index++;
            }
            //执行Sql语句
             count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //给占位符传值
        return count;
    }
    //selectOne

    /**
     * 执行查询语句，返回一个对象。该方法只适合返回一条语句的sql语句
     * @param sqlId
     * @param param
     * @return
     */
    public Object selectOne(String sqlId,Object param){
     Object obj=null;
        try {
            Connection connection=factory.getTransaction().getConnection();
            MappedStatement mappedStatement = factory.getMappedStatementMap().get(sqlId);
            String godbatissql = mappedStatement.getSql();
            String sql=godbatissql.replaceAll("#\\{[a-zA-Z0-9_$]*}","?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,param.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            //要封装的类型
            String resultType = mappedStatement.getResultType();
            if (resultSet.next()){
                //获取resultType的Class
                Class<?> resultTypeClass = Class.forName(resultType);
                //调用无参构造方法创建对象
                obj = resultTypeClass.newInstance();//Object obj=new User();
                //给User类的id，name,age属性赋值
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String propertyName = rsmd.getColumnName(i+1);
                    String setMethodName="set"+propertyName.toUpperCase().charAt(0)+propertyName.substring(1);
                    //获取set方法
                    Method setMthod = resultTypeClass.getDeclaredMethod(setMethodName, String.class);
                    //调用setMthod给属性赋值
                    setMthod.invoke(obj,resultSet.getString(propertyName));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return obj;
    }
    /**
     * 提交事务
     */
    public void commit(){
        factory.getTransaction().commit();
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        factory.getTransaction().rollback();
    }

    /**
     * 关闭事务
     */
    public void close(){
        factory.getTransaction().close();
    }

}
