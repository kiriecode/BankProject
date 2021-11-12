package BankEx;

public class NoSuchAccountException extends Exception{
    public NoSuchAccountException(int accid) {
        super("抱歉！账户编号" + accid + "不存在");
    }
}
