package zui.service.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zui.domain.Account;
import zui.mapper.AccountMapper;
import zui.service.AccountService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void save(Account account) throws IOException {
        accountMapper.save(account);
    }

    @Override
    public List<Account> findAll() throws IOException {
        return accountMapper.findAll();
    }


//    @Override
//    public void save(Account account) throws IOException {
//        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
//        accountMapper.save(account);
//        sqlSession.commit();
//        sqlSession.close();
//
//    }
//
//    @Override
//    public List<Account> findAll() throws IOException {
//        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
//        List<Account> accountList = accountMapper.findAll();
//        sqlSession.commit();
//        sqlSession.close();
//        System.out.println(accountList);
//        return accountList;
//    }
}
