package Test;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankEx.InputValueException;
import BankEx.NoSuchAccountException;
import BankEx.NotEnoughMoneyException;
import BankPo.Account;

import javax.swing.*;
import java.sql.SQLException;

public class TestDelectAccount {
    public static void main(String[] args) throws NoSuchAccountException, NotEnoughMoneyException, SQLException, ClassNotFoundException, InputValueException {
        BankBiz bankBiz = new BankBizImpl();
        try {
            System.out.println(bankBiz.delAccount(4));
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户异常", JOptionPane.ERROR_MESSAGE);
        }
    }
}
