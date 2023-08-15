package com.szq.mybatis.core;

import java.sql.Connection;

/**
 * 事务管理器接口
 * 所有事务管理器都应该遵循该规范
 * JDBC管理器，MANGED事务管理器都应该实现接口
 * Transaction事务管理器：提供事务的方法
 */
public interface Transaction {
    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 关闭事务
     */
    void close();

    /**
     * 真正开启数据库连接
     */
    void opeanConnection();

    /**
     * 获取数据库连接对象
     * @return
     */
    Connection getConnection();
}
