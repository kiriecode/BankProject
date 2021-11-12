package Test;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankPo.Account;
import BankPo.Log;

import java.sql.SQLException;
import java.util.List;

public class TestSelectAll {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BankBiz bankBiz = new BankBizImpl();
        List<Account> listAccount = bankBiz.selectAllAccounts();
        for(Account account : listAccount) {
            System.out.println(account);
        }
        List<Log> listLog = bankBiz.selectAllLogs();
        for(Log log : listLog) {
            System.out.println(log);
        }
    }
}
