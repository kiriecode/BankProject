package BankPo;

public class Account {
    private int accid;
    private String name;
    private int money;

    public Account() {
        super();
    }

    public Account(int accid, String name, int money) {
        this.accid = accid;
        this.name = name;
        this.money = money;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accid=" + accid +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
