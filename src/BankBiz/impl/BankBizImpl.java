package BankBiz.impl;

import BankBiz.BankBiz;
import BankDao.AccountDao;
import BankDao.LogDao;
import BankDao.impl.AccountDaoImpl;
import BankDao.impl.LogDaoImpl;
import BankEx.InputValueException;
import BankPo.Account;
import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public class BankBizImpl implements BankBiz {

    AccountDao accountDao = new AccountDaoImpl();
    LogDao logDao = new LogDaoImpl();

    @Override
    public boolean openAccount(Account account) throws SQLException, ClassNotFoundException, InputValueException {
        if(account.getMoney() < 0) {
            throw new InputValueException(account.getMoney());
        }
        int accid = accountDao.addAccount(account);
        Log log = new Log();
        log.setAccid(accid);
        log.setName(account.getName());
        log.setMoney(account.getMoney());
        int res = logDao.addLog(log);
        return accid * res > 0;
    }

    @Override
    public boolean delAccount(Account account) {
        return false;
    }

    @Override
    public boolean saveMoney(int accid, double value) {
        return false;
    }

    @Override
    public boolean fetchMoney(int accid, double value) {
        return false;
    }

    @Override
    public boolean turnMoney(int accidOut, int accidIn, double value) {
        return false;
    }

    @Override
    public List<Account> selectAllAccounts() {
        return null;
    }

    @Override
    public List<Log> selectAllLogs() {
        return null;
    }
}
