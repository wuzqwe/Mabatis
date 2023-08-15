package com.szq.bank.service;

import com.szq.bank.exception.MoneyNotEnoughException;
import com.szq.bank.exception.TransferException;

/**
 * 账户业务类
 *
 */
public interface AccountService {
    /**
     * 账户转账业务
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转入金额
     */
    void transfer(String fromActno,String toActno,double money) throws MoneyNotEnoughException, TransferException;
}
