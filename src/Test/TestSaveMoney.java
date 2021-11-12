package Test;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankEx.InputValueException;
import BankEx.NoSuchAccountException;

import javax.swing.*;
import java.sql.SQLException;

public class TestSaveMoney {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BankBiz bankBiz = new BankBizImpl();
        try {
            System.out.println(bankBiz.saveMoney(1, -20));
        } catch (NoSuchAccountException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "金额异常", JOptionPane.ERROR_MESSAGE);
        }
    }
}
