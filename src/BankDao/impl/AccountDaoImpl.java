package BankDao.impl;

import BankDao.AccountDao;
import BankDao.BaseDao;
import BankPo.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends BaseDao implements AccountDao {
    @Override
    public int addAccount(Account account) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "insert into account values(null,? ,? )";
        pst = con.prepareStatement(sql);
        pst.setString(1, account.getName());
        pst.setInt(2, account.getMoney());
        int res = pst.executeUpdate();
        return res;
    }

    @Override
    public Account selectById(int accid) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "select * from account where accid = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, accid);
        rs = pst.executeQuery();
        Account account = null;
        if(rs.next()) {
            account = new Account();
            account.setAccid(rs.getInt("accid"));
            account.setName(rs.getString("name"));
            account.setMoney(rs.getInt("money"));
        }
        return account;
    }

    @Override
    public int updateAccount(int accid, double value) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "update account set money = money + ? where accid = ?";
        pst = con.prepareStatement(sql);
        pst.setDouble(1, value);
        pst.setInt(2, accid);
        int res = pst.executeUpdate();
        return res;
    }

    @Override
    public int deleteAccount(int accid) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "delete from account where accid = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, accid);
        int res = pst.executeUpdate();
        return res;
    }

    @Override
    public List<Account> selectAll() throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "select * from account";
        pst = con.prepareStatement(sql);
        List<Account> accounts = new ArrayList<Account>();
        rs = pst.executeQuery();
        while(rs.next()) {
            Account account = new Account();
            account.setAccid(rs.getInt("accid"));
            account.setName(rs.getString("name"));
            account.setMoney(rs.getInt("money"));
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public int turnMoney(int accidOut, int accidIn, int value) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql1 = "update account set money = money - ? where accid = ?";
        String sql2 = "update account set money = money + ? where accid = ?";
        con.setAutoCommit(false);

        pst = con.prepareStatement(sql1);
        pst.setDouble(1, value);
        pst.setInt(2, accidIn);
        int res1 = pst.executeUpdate();

        pst = con.prepareStatement(sql2);
        pst.setDouble(1, value);
        pst.setInt(2, accidIn);
        int res2 = pst.executeUpdate();

        if(res1 * res2 > 0) {
            con.commit();
        } else {
            con.rollback();
        }
        return res1 * res2;
    }
}
