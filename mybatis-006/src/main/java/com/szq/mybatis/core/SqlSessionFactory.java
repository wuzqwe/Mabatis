package com.szq.mybatis.core;

import java.util.Map;

/**
 * SqlSessionFactory对象：
 * 一个数据库对应一个SqlSessionFactory对象
 * 通过SwlSessionFactory对象可以获取SqlSession对象，开启会话
 * 一个SqlSessionFactory对象可以开启多个SqlSession对象
 */
public class SqlSessionFactory {
    /**
     * 事务管理器
     * 事务管理器可以灵活切换的
     * SqlSessionFactory类中的事务管理器应该面向接口编程的
     * SqlSessionFactory类中应该有一个事务管理器的接口
     */
    private Transaction transaction;


    /**
     *
     * 数据源属性
     */
    /**
     * 存放SQL语句的Map集合
     * key是sqlId
     * value是对应的SQL标签信息对象
     */
    /**
     * 存放SQL语句的Map集合
     * key是sqlId
     * value是对应的SQl标签信息对象
     */
    private Map<String,MappedStatement> mappedStatementMap;

    public SqlSessionFactory() {
    }

    public SqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatementMap) {
        this.transaction = transaction;
        this.mappedStatementMap = mappedStatementMap;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    /**
     * 获取Sql会话对象
     * @return
     */
    public SqlSession opeanSession(){
        //开启会话就是开启连接(连接打开)
        transaction.opeanConnection();
        //创建SqlSession对象
        SqlSession sqlSession = new SqlSession(this);
        return sqlSession;
    }
}
