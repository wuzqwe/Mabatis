package com.szq.mybatis.core;

import java.sql.Connection;

/**
 * Managed事务管理器
 */
public class ManagedTransaction implements Transaction{
    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void opeanConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
