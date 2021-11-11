package BankPo;

public class Log {
    private int logid;
    private int accid;
    private String name;
    private double money;
    private String date;

    public Log() {
    }

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logid=" + logid +
                ", accid=" + accid +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", date='" + date + '\'' +
                '}';
    }
}
