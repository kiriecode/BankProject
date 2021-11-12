package Test;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankEx.InputValueException;
import BankEx.NoSuchAccountException;
import BankEx.NotEnoughMoneyException;

import javax.swing.*;
import java.sql.SQLException;

public class TestTurnMoney {
    public static void main(String[] args) throws NoSuchAccountException, NotEnoughMoneyException, SQLException, ClassNotFoundException, InputValueException {
        BankBiz bankBiz = new BankBizImpl();
        try {
            System.out.println(bankBiz.turnMoney(4, 2, -10000));
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "用户异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "金额异常", JOptionPane.ERROR_MESSAGE);
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "余额不足", JOptionPane.ERROR_MESSAGE);
        }
    }
}
