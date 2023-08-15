package com.szq.bank.dao.impl;

import com.szq.bank.dao.AccountDao;
import com.szq.bank.pojo.Account;
import com.szq.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession session = SqlSessionUtil.opeanSession();
        return (Account) session.selectOne("account.selectByActno", actno);
    }

    @Override
    public int updateByActno(Account act) {
        SqlSession session = SqlSessionUtil.opeanSession();
        return  session.update("account.updateByActno", act);
    }
}
