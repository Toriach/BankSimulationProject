package bank;

import other.Account;
import other.ClientAccount;
import other.CompanyBankAccount;
import other.PersonalBankAccount;

import java.util.ArrayList;
import java.util.List;

public class BankBackEnd {
    public BankBackEnd() {
        createClientAccount("Tomasz", "Sowiński", "0123456789", 0);

        createClientAccount("Adam", "Mickiewicz", "5393493443", 0);

        createClientAccount("Toriach Corp", "", "", 1);

        createBankAccount(0, BankDatabase.clientsList.get(1));
        createBankAccount(0, BankDatabase.clientsList.get(1));

        BankDatabase.accountsList.get(3).setBalance(127.45);
        BankDatabase.accountsList.get(4).setBalance(24.4);
        //   BankDatabase.accountsList.get(0).setBalance(0);

        // removeBankAccountWithZeroBalance();
        // removeClientAccountWithoutBankAccount();

        System.out.println(BankDatabase.accountsList.get(2).getBalance());
        receiveExternalTransaction(BankDatabase.accountsList.get(2).getAccountNumber(), 22.36);
        System.out.println(BankDatabase.accountsList.get(2).getBalance());
        System.out.println("--------------------------");

        receiveExternalTransaction("blabla", 22.36);
        System.out.println("--------------------------");

        receiveInternalTransaction(BankDatabase.accountsList.get(2).getAccountNumber(), BankDatabase.accountsList.get(0).getAccountNumber(), 15.4);
        System.out.println("--------------------------");
        receiveInternalTransaction(BankDatabase.accountsList.get(2).getAccountNumber(), BankDatabase.accountsList.get(0).getAccountNumber(), 55);
        System.out.println("--------------------------");

        withdrawFounds(BankDatabase.accountsList.get(1).getAccountNumber(), 50);
        System.out.println("--------------------------");
        withdrawFounds(BankDatabase.accountsList.get(1).getAccountNumber(), 5);

        System.out.println("--------------------------");
        showListOfClientBankAccounts(BankDatabase.clientsList.get(1));

        System.out.println("--------------------------");
        showListOfClientBankAccountsWithBalance(BankDatabase.clientsList.get(1));

        System.out.println("--------------------------");
        showAllBankAccounts();

        System.out.println("--------------------------");
        showAllBankAccountsWithBalance();
    }

    private void createClientAccount(String name, String surname, String personalIdentityNumber, int AccountTypeById) {
        ClientAccount clientAccount = new ClientAccount(name, surname, personalIdentityNumber);
        BankDatabase.clientsList.add(clientAccount);

        createBankAccount(AccountTypeById, clientAccount);
    }

    public void createBankAccount(int AccountTypeById, ClientAccount clientAccount) {
        Account account = null;
        switch (AccountTypeById) {
            case 0:
                account = new PersonalBankAccount(clientAccount);
                break;
            case 1:
                account = new CompanyBankAccount(clientAccount);
                break;
            default:
                System.out.println("Wrong account type id!");
        }
        if (account != null) {
            BankDatabase.accountsList.add(account);
        } else {
            System.out.println("Account creating failed!");
        }
    }

    public void receiveInternalTransaction(String sendingAccountNumber, String receivingAccountNumber, double amountToTransfer) {
        /**
         * tak, wiem, rozbić to na mniejsze metody i mniej ifów :)
         */
        boolean sendingAccountExist = false;
        boolean receivingAccountExist = false;
        int sendingAccountINDEX = -1;
        int receivingAccountINDEX = -1;

        for (int i = 0; i < BankDatabase.accountsList.size(); i++) {
            if (BankDatabase.accountsList.get(i).getAccountNumber().equals(sendingAccountNumber)) {
                sendingAccountExist = true;
                sendingAccountINDEX = i;
                break;
            }
        }
        for (int i = 0; i < BankDatabase.accountsList.size(); i++) {
            if (BankDatabase.accountsList.get(i).getAccountNumber().equals(receivingAccountNumber)) {
                receivingAccountExist = true;
                receivingAccountINDEX = i;
                break;
            }
        }
        if (!sendingAccountExist) {
            System.out.println("ERROR , sending from non existing account?");
        } else if (!receivingAccountExist) {
            System.out.println("ERROR , target account don't exist!");
        } else if (sendingAccountExist && receivingAccountExist) {
            if (BankDatabase.accountsList.get(sendingAccountINDEX).getBalance() >= amountToTransfer) {
                BankDatabase.accountsList.get(sendingAccountINDEX).setBalance(BankDatabase.accountsList.get(sendingAccountINDEX).getBalance() - amountToTransfer);
                System.out.println("transferring " + amountToTransfer + " zł from account number: " + BankDatabase.accountsList.get(sendingAccountINDEX).getAccountNumber()
                        + " to account number: " + BankDatabase.accountsList.get(receivingAccountINDEX).getAccountNumber());

                BankDatabase.accountsList.get(receivingAccountINDEX).setBalance(BankDatabase.accountsList.get(receivingAccountINDEX).getBalance() + amountToTransfer);
                System.out.println("Account number: " + BankDatabase.accountsList.get(receivingAccountINDEX).getAccountNumber()
                        + " received " + amountToTransfer + " zł from accound number: " + BankDatabase.accountsList.get(sendingAccountINDEX).getAccountNumber());
            } else {
                System.out.print("You cannot sand " + amountToTransfer + " zł, because You only have ");
                System.out.printf("%.2f zł in Your account!\n", BankDatabase.accountsList.get(sendingAccountINDEX).getBalance());
            }
        }
    }

    public void receiveExternalTransaction(String receivingAccountNumber, double amountToTransfer) {
        int checkIfTargetAccountWasFound = 0;
        for (Account targetBankAccount : BankDatabase.accountsList) {
            if (targetBankAccount.getAccountNumber().equals(receivingAccountNumber)) {
                targetBankAccount.setBalance(targetBankAccount.getBalance() + amountToTransfer);
                System.out.println(amountToTransfer + " zł successfully transferred!");
                checkIfTargetAccountWasFound = 1;
            }
        }

        if (checkIfTargetAccountWasFound == 0) {
            System.out.println("Target account not found!");
        }
    }

    public void withdrawFounds(String accountNumberToWithdraw, int amount) {
        int checkIfTargetAccountWasFound = 0;
        for (Account accountToWithdraw : BankDatabase.accountsList) {
            if (accountToWithdraw.getAccountNumber().equals(accountNumberToWithdraw)) {
                if (accountToWithdraw.getBalance() >= amount) {
                    accountToWithdraw.setBalance(accountToWithdraw.getBalance() - amount);
                    System.out.println("You withdrawn " + amount + " zł from account number: " + accountToWithdraw.getAccountNumber() + " new balance: " + accountToWithdraw.getBalance());
                } else {
                    System.out.print("You cannot withdraw " + amount + " zł, because You only have ");
                    System.out.printf("%.2f zł in Your account!\n", accountToWithdraw.getBalance());
                }
                checkIfTargetAccountWasFound = 1;
            }
        }
        if (checkIfTargetAccountWasFound == 0) {
            System.out.println("Target account not found!");
        }
    }

    public void showListOfClientBankAccounts(ClientAccount clientAccount) {
        System.out.println("Client: " + clientAccount.getName() + " " + clientAccount.getSurname() + " ID: " + clientAccount.getClientID());
        System.out.println("Have " + clientAccount.getClientAccountsIDList().size() + " account/s.");
        System.out.println("Account/s list: ");
        for (int i = 0; i < clientAccount.getClientAccountsIDList().size(); i++) {
            System.out.println("   - " + clientAccount.getClientAccountsIDList().get(i));
        }
    }

    public void showListOfClientBankAccountsWithBalance(ClientAccount clientAccount) {
        System.out.println("Client: " + clientAccount.getName() + " " + clientAccount.getSurname() + " ID: " + clientAccount.getClientID());
        System.out.println("Have " + clientAccount.getClientAccountsIDList().size() + " account/s.");
        System.out.println("Account/s list with balance: ");
        for (int i = 0; i < clientAccount.getClientAccountsIDList().size(); i++) {
            for (int j = 0; j < BankDatabase.accountsList.size(); j++) {
                if (clientAccount.getClientAccountsIDList().get(i).equals(BankDatabase.accountsList.get(j).getAccountNumber())) {
                    System.out.println("   - " + BankDatabase.accountsList.get(j).getAccountNumber() + "  - " + BankDatabase.accountsList.get(j).getBalance() + " zł.");
                }
            }
        }
    }

    // bank administration tools //

    public void removeBankAccountWithZeroBalance() {
        List<Account> temporalBankAccountsList = new ArrayList<>();
        List<String> temporalBankAccountsIDListToRemove = new ArrayList<>();
        for (Account bankAccountsToCheck : BankDatabase.accountsList) {
            if (bankAccountsToCheck.getBalance() == 0) {
                temporalBankAccountsList.add(bankAccountsToCheck);
                temporalBankAccountsIDListToRemove.add(bankAccountsToCheck.getAccountNumber());
            }
        }
        for (ClientAccount clientAccount : BankDatabase.clientsList) {
            clientAccount.getClientAccountsIDList().removeAll(temporalBankAccountsIDListToRemove);
        }
        BankDatabase.accountsList.removeAll(temporalBankAccountsList);
    }

    public void removeClientAccountWithoutBankAccount() {
        List<ClientAccount> temporalClientAccountsList = new ArrayList<>();
        for (ClientAccount ClientAccountToCheck : BankDatabase.clientsList) {
            if (ClientAccountToCheck.getClientAccountsIDList().size() == 0) {
                temporalClientAccountsList.add(ClientAccountToCheck);
            }
        }
        BankDatabase.clientsList.removeAll(temporalClientAccountsList);
    }

    public void showAllBankAccounts() {
        System.out.println("List of all account in bank: ");
        BankDatabase.accountsList.stream().forEach(account -> System.out.println("   - " + account.getAccountNumber()));
    }

    public void showAllBankAccountsWithBalance() {
        System.out.println("List of all account in bank and balance: ");
        BankDatabase.accountsList.stream().forEach(account -> System.out.printf("   - %s %.2f zł\n" , account.getAccountNumber(), account.getBalance()));
    }

}