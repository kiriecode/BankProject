package BankDao;

import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public interface LogDao {
    // note 向log表中增加一条记录
    public int addLog(Log log) throws SQLException, ClassNotFoundException;
    // note 从log表中查询所有记录，封装成列表
    public List<Log> selectAll() throws SQLException, ClassNotFoundException;
}
