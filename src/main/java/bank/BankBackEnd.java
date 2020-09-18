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


        BankDatabase.accountsList.get(0).setBalance(0);
       
        removeBankAccountWithZeroBalance();
        removeClientAccountWithoutBankAccount();
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
}