package zui.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zui.dao.AccountDao;
import zui.service.AccountService;

@Service("accountService")
//@Transactional(isolation = Isolation.DEFAULT0
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountDao accountDao;
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void transfer(String outMan, String inMan, double money) {
//        开启事务
        accountDao.out(outMan,money);
        int i = 1/0;
        accountDao.in(inMan,money);
//        提交事务
    }
}
