package BankEx;

public class InputValueException extends Exception{
    public InputValueException(double value) {
        super("您输入的金额是: " + value + "，它是一个负数！");
    }
}
