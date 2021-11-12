package BankBiz;

import BankEx.InputValueException;
import BankEx.NoSuchAccountException;
import BankEx.NotEnoughMoneyException;
import BankPo.Account;
import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public interface BankBiz {
    // 开户
    public boolean openAccount(Account account) throws SQLException, ClassNotFoundException, InputValueException;
    // 销户
    public boolean delAccount(int accid) throws SQLException, ClassNotFoundException, NoSuchAccountException, NotEnoughMoneyException, InputValueException;
    // 存钱
    public boolean saveMoney(int accid, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException;
    // 取钱
    public boolean fetchMoney(int accid, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException, NotEnoughMoneyException;
    // 转账
    public boolean turnMoney(int accidOut, int accidIn, double value) throws SQLException, ClassNotFoundException, NoSuchAccountException, InputValueException, NotEnoughMoneyException;
    // 查询所有账户
    public List<Account> selectAllAccounts() throws SQLException, ClassNotFoundException;
    // 查询所有日志
    public List<Log> selectAllLogs() throws SQLException, ClassNotFoundException;
}
