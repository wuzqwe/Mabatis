package com.szq.mybatis.core;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 *JDBC事务管理器
 */
public class JdbcTransaction implements Transaction{
    /**
     * 数据源属性
     */
    private DataSource dataSource;

    /**
     * 自动提交
     * true自动提交
     * false手动提交
     */
    private boolean autoCommi;

    private Connection connection;

    /**
     * 创建事务管理器对象
     * @param dataSource
     * @param autoCommi
     */
    public JdbcTransaction(DataSource dataSource, boolean autoCommi) {
        this.dataSource = dataSource;
        this.autoCommi = autoCommi;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void opeanConnection(){
        if (connection == null) {
            try {
                connection=dataSource.getConnection();
                //开启事务
                connection.setAutoCommit(autoCommi);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
