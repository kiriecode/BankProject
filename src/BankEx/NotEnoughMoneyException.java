package BankEx;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException(double money, double value) {
        super("账户现有金额：" + money + "元，取款金额为：" + value + "元，余额不足！");
    }
}
