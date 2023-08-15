package com.szq.bank.service.impl;

import com.szq.bank.dao.AccountDao;
import com.szq.bank.dao.impl.AccountDaoImpl;
import com.szq.bank.exception.MoneyNotEnoughException;
import com.szq.bank.exception.TransferException;
import com.szq.bank.pojo.Account;
import com.szq.bank.service.AccountService;
import com.szq.bank.utils.GenerateDaoProxy;
import com.szq.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {
    //自己封装的
    //private AccountDao accountDao= (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.opeanSession(),AccountDao.class);
    //在mybatis当中，mybatis提供了相关的机制。也可以动态为我们生成dao接口的实现类（代理类：dao接口的代理）
    //mybatis当中实际上采用了代理模式。在内存中生成dao接口的代理类，然后创建代理类的实例
    //使用mybatis的这种代理机制的前提：SqlMapper.xml文件中namespace必须是dao接口的全限定名称，id必须是dao接口的方法名
    private AccountDao accountDao=SqlSessionUtil.opeanSession().getMapper(AccountDao.class);

    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        SqlSession sqlSession= SqlSessionUtil.opeanSession();
        //1.判断转出账号的余额是否充足（select）
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance()<money) {
            //2.如果转出账户不足，提示用户
            throw new MoneyNotEnoughException("对不起，余额不足");
        }
        //3.如果转出账户余额充足，更新转出账户余额（update）
        //先更新内存中java对象account对象
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance()-money);
        toAct.setBalance(toAct.getBalance()+money);
        //4.更新转入账户余额（update）
        int count = accountDao.updateByActno(fromAct);
        count+= accountDao.updateByActno(toAct);
        if (count!=2) {
            throw new TransferException("转账异常 ，未知原因");
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);


    }
}
