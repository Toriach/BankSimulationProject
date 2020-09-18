package other;

public abstract class Account {
    private String AccountTypeName;
    private double balance;
    private String accountNumber;

    public String getAccountTypeName() {
        return AccountTypeName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
