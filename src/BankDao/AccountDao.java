package BankDao;

import BankPo.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AccountDao {
    // note 向account表增加一条记录
    public int addAccount(Account account) throws SQLException, ClassNotFoundException;
    // note 根据accid查询账户信息
    public Account selectById(int accid) throws SQLException, ClassNotFoundException;
    // note 在account表中修改一条记录，根据accid将money变化为value元钱
    public int updateAccount(int accid, double value) throws SQLException, ClassNotFoundException;
    // note 在account表中删除一条记录，按acid删除
    public int deleteAccount(int accid) throws SQLException, ClassNotFoundException;
    // note 在account表中查询所有记录，封装成列表
    public List<Account> selectAll() throws SQLException, ClassNotFoundException;
    // note accidOut向accidIn账户转入value元钱
    public int turnMoney(int accidOut, int accidIn, double value) throws SQLException, ClassNotFoundException;
}
