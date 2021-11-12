package BankAview;

import BankBiz.BankBiz;
import BankBiz.impl.BankBizImpl;
import BankEx.InputValueException;
import BankEx.NoSuchAccountException;
import BankEx.NotEnoughMoneyException;
import BankPo.Account;
import BankPo.Log;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static BankBiz bankBiz = new BankBizImpl();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner re = new Scanner(System.in);

        while(true) {
            System.out.println("欢迎使用K银行管理系统");
            System.out.println("请使用如下操作");
            System.out.println("1-开户 2-销户 3-存钱 4-取钱 5-转账 0-退出系统");

            int op = re.nextInt();
            if(op == 0) {
                break;
            } else if(op == 1) {
                openAccount();
            } else if(op == 2) {
                delAccount();
            } else if(op == 3) {
                saveMoney();
            } else if(op == 4) {
                fetchMoney();
            } else if(op == 5) {
                turnMoney();
            } else {
                continue;
            }
            List<Account> accountList = selectAllAccounts();
            List<Log> logList = selectAllLogs();

            System.out.println("所有账户信息如下");
            for(Account account : accountList) {
                System.out.println(account);
            }
            System.out.println("所有日志信息如下");
            for(BankPo.Log log : logList) {
                System.out.println(log);
            }
        }
        System.out.println("感谢您的使用");

        re.close();
    }
    public static void openAccount() throws SQLException, ClassNotFoundException {
        System.out.println("请输入账户名称：");
        String name = sc.next();
        System.out.println("请输入账户初始金额：");
        double initMoney = sc.nextDouble();
        Account account = new Account(0, name, initMoney);
        boolean result = false;
        try {
            result = bankBiz.openAccount(account);
        } catch (InputValueException e) {
            System.out.println("开户金额异常");
            JOptionPane.showMessageDialog(null, e.getMessage(), "开户金额异常", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(result ? "开户成功" : "开户失败");

    }
    public static void delAccount() throws SQLException, ClassNotFoundException {
        System.out.println("请输入待销户的账户编号");
        int accid = sc.nextInt();
        boolean result = false;
        try {
            result = bankBiz.delAccount(accid);
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户状态异常", JOptionPane.ERROR_MESSAGE);
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户金额异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "输入金额异常", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(result ? "销户成功" : "销户失败");
    }
    public static void saveMoney() throws SQLException, ClassNotFoundException {
        System.out.println("请输入存钱账户编号：");
        int accid = sc.nextInt();
        System.out.println("请输入存钱金额：");
        double value = sc.nextDouble();
        boolean result = false;
        try {
            result = bankBiz.saveMoney(accid, value);
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户状态异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "输入金额异常", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(result ? "存钱成功" : "存钱失败");
    }
    public static void fetchMoney() throws SQLException, ClassNotFoundException {
        System.out.println("请输入取钱账户编号：");
        int accid = sc.nextInt();
        System.out.println("请输入取钱金额：");
        double value = sc.nextDouble();
        boolean result = false;
        try {
            result = bankBiz.fetchMoney(accid, value);
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户状态异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "输入金额异常", JOptionPane.ERROR_MESSAGE);
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户金额异常", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(result ? "存钱成功" : "取钱失败");
    }
    public static void turnMoney() throws SQLException, ClassNotFoundException {
        System.out.println("请输入转出账户编号：");
        int accidOut = sc.nextInt();
        System.out.println("请输入转入账户编号：");
        int accidIn = sc.nextInt();
        System.out.println("请输入转账金额：");
        double value = sc.nextDouble();
        boolean result = false;
        try {
            result = bankBiz.turnMoney(accidOut, accidIn, value);
        } catch (NoSuchAccountException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户状态异常", JOptionPane.ERROR_MESSAGE);
        } catch (InputValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "输入金额异常", JOptionPane.ERROR_MESSAGE);
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "账户金额异常", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(result ? "转账成功" : "转账失败");
    }
    public static List<BankPo.Account> selectAllAccounts() throws SQLException, ClassNotFoundException {
        return bankBiz.selectAllAccounts();
    }
    public static List<BankPo.Log> selectAllLogs() throws SQLException, ClassNotFoundException {
        return bankBiz.selectAllLogs();
    }
}
