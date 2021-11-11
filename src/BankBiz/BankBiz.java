package BankBiz;

import BankEx.InputValueException;
import BankPo.Account;
import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public interface BankBiz {
    // 开户
    public boolean openAccount(Account account) throws SQLException, ClassNotFoundException, InputValueException;
    // 销户
    public boolean delAccount(Account account);
    // 存钱
    public boolean saveMoney(int accid, double value);
    // 取钱
    public boolean fetchMoney(int accid, double value);
    // 转账
    public boolean turnMoney(int accidOut, int accidIn, double value);
    // 查询所有账户
    public List<Account> selectAllAccounts();
    // 查询所有日志
    public List<Log> selectAllLogs();
}
