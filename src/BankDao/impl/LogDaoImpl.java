package BankDao.impl;

import BankDao.AccountDao;
import BankDao.BaseDao;
import BankDao.LogDao;
import BankPo.Account;
import BankPo.Log;
import BankUtil.DateFormat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDaoImpl extends BaseDao implements LogDao {

    @Override
    public int addLog(Log log) throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "insert into log values(null, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setInt(1, log.getAccid());
        pst.setString(2, log.getName());
        pst.setDouble(3, log.getMoney());
        pst.setString(4, DateFormat.nowToDateTime());
        int res = pst.executeUpdate();
        return res;
    }

    @Override
    public List<Log> selectAll() throws SQLException, ClassNotFoundException {
        con = getConnection();
        String sql = "select * from log";
        pst = con.prepareStatement(sql);
        List<Log> logs = new ArrayList<Log>();
        rs = pst.executeQuery();
        while(rs.next()) {
            Log log = new Log();
            log.setLogid(rs.getInt(1));
            log.setAccid(rs.getInt(2));
            log.setName(rs.getString(3));
            log.setMoney(rs.getDouble(4));
            log.setDate(rs.getString(5));
            logs.add(log);
        }
        return logs;
    }
}
