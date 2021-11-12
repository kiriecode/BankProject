package BankBiz.impl;

import BankBiz.BankBiz;
import BankDao.AccountDao;
import BankDao.LogDao;
import BankDao.impl.AccountDaoImpl;
import BankDao.impl.LogDaoImpl;
import BankEx.InputValueException;
import BankEx.NoSuchAccountException;
import BankEx.NotEnoughMoneyException;
import BankPo.Account;
import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public class BankBizImpl implements BankBiz {

    AccountDao accountDao = new AccountDaoImpl();
    LogDao logDao = new LogDaoImpl();

    @Override
    public boolean openAccount(Account account) throws SQLException, ClassNotFoundException, InputValueException {
        // explain 1.捕获金额异常
        if(account.getMoney() < 0) {
            throw new InputValueException(account.getMoney());
        }
        // explain 2.向account中添加一条记录
        int accid = accountDao.addAccount(account);
        // explain 3.向log表中添加一条记录
        Log log = new Log();
        log.setAccid(accid);
        log.setName(account.getName());
        log.setMoney(account.getMoney());
        int res = logDao.addLog(log);
        return accid * res > 0;
    }

    @Override
    public boolean delAccount(int accid) throws SQLException, ClassNotFoundException, NoSuchAccountException, NotEnoughMoneyException, InputValueException {
        // explain 1.取钱：将accid中对应的money金额全部取出
        Account account = accountDao.selectById(accid);
        if(account == null) {
            throw new NoSuchAccountException(accid);
        }
        boolean res1 = fetchMoney(accid, account.getMoney());
        // explain 2.从account中删除accid
        int res2 = accountDao.deleteAccount(accid);
        return res1 && (res2 > 0);
    }

    @Override
    public boolean saveMoney(int accid, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException {
        // explain 1.从account中根据accid返回当前用户情况并检查异常
        Account account = accountDao.selectById(accid);
        if(account == null) {
            throw new NoSuchAccountException(accid);
        }
        if(value < 0) {
            throw new InputValueException(value);
        }
        // explain 2.根据accid对应的用户信息更新价值：accid的money列增加value元
        int res1 = accountDao.updateAccount(accid, value);
        // explain 3.向log表中添加一条记录
        Log log = new Log();
        log.setAccid(account.getAccid());
        log.setName(account.getName());
        log.setMoney(account.getMoney() + value);
        int res2 = logDao.addLog(log);
        return res1 * res2 > 0;
    }

    @Override
    public boolean fetchMoney(int accid, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException, NotEnoughMoneyException {
        // explain 1.从account中根据accid返回当前用户情况并检查异常
        Account account = accountDao.selectById(accid);
        if(account == null) {
            throw new NoSuchAccountException(accid);
        }
        if(value < 0) {
            throw new InputValueException(value);
        }
        if(account.getMoney() < value) {
            throw new NotEnoughMoneyException(account.getMoney(), value);
        }
        // explain 2.根据accid对应的用户信息更新价值：accid的money列减少value元
        int res1 = accountDao.updateAccount(accid, -1 * value);
        // explain 3.向log表中添加一条记录
        Log log = new Log();
        log.setAccid(account.getAccid());
        log.setName(account.getName());
        log.setMoney(account.getMoney() - value);
        int res2 = logDao.addLog(log);
        return res1 * res2 > 0;
    }

    @Override
    public boolean turnMoney(int accidOut, int accidIn, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException, NotEnoughMoneyException {
        // explain 由accidOut, accidIn得到两个account
        Account account1 = accountDao.selectById(accidOut);
        Account account2 = accountDao.selectById(accidIn);
        if(account1 == null) {
            throw new NoSuchAccountException(accidOut);
        }
        if(account2 == null) {
            throw new NoSuchAccountException(accidIn);
        }
        if(value < 0) {
            throw new InputValueException(value);
        }
        if(account1.getMoney() < value) {
            throw new NotEnoughMoneyException(account1.getMoney(), value);
        }
        // explain 2.account1减少value元，account2增加value元
        int turnMoney = accountDao.turnMoney(accidOut, accidIn, value);
        // explain 3.增加两条记录到log表中
        Log log1 = new Log();
        log1.setAccid(accidOut);
        log1.setName(account1.getName());
        log1.setMoney(account1.getMoney() - value);
        int addLog1 = logDao.addLog(log1);

        Log log2 = new Log();
        log2.setAccid(accidIn);
        log2.setName(account2.getName());
        log2.setMoney(account2.getMoney() + value);
        int addLog2 = logDao.addLog(log2);
        return turnMoney * addLog1 * addLog2 > 0;
    }

    @Override
    public List<Account> selectAllAccounts() throws SQLException, ClassNotFoundException {
        return accountDao.selectAll();
    }

    @Override
    public List<Log> selectAllLogs() throws SQLException, ClassNotFoundException {
        return logDao.selectAll();
    }
}
