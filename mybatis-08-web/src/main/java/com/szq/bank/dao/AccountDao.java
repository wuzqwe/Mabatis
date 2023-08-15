package com.szq.bank.dao;

import com.szq.bank.pojo.Account;

/**
 * 账户的DAO对象。负责t_act表中的CRUD
 */
public interface AccountDao {
    /**
     * 根据账号查询账户信息
     * @param actno
     * @return
     */
    Account selectByActno(String actno);

    /**
     * 更新账户信息
     * @param act 被更新的账户对象
     * @return
     */
    int updateByActno(Account act);
}
