package other;

import java.util.Random;

public class CompanyBankAccount extends Account{
    private String AccountTypeName = "Company account";
    private double balance;
    private String accountNumber = "";
    private Random random;
    private ClientAccount clientAccount;

    public CompanyBankAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
        balance = 5;
        generateUniqeAccountNumber();
        clientAccount.getClientAccountsIDList().add(accountNumber);
    }

    private void generateUniqeAccountNumber() {
        random = new Random();
        for (int i = 0; i < 10 ; i++) {
            accountNumber += ""+ random.nextInt(10);
        }
    }

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
