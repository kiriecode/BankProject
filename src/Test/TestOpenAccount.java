package Test;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankEx.InputValueException;
import BankPo.Account;

import javax.swing.*;
import java.sql.*;

public class TestOpenAccount {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InputValueException {
        Account account = new Account();
        account.setName("wang fang");
        account.setMoney(-1250);
        BankBiz bankBiz = new BankBizImpl();
        try {
            boolean res = bankBiz.openAccount(account);
        } catch (InputValueException e) {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "金额异常", JOptionPane.ERROR_MESSAGE);
        }
    }
}
