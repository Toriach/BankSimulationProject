package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientAccount {
    private String name;
    private String surname;
    private String personalIdentityNumber;
    private String clientID = "";
    private List<String> clientAccountsIDList= new ArrayList();
    private  Random random;
    public ClientAccount(String name, String surname, String personalIdentityNumber) {
        this.name = name;
        this.surname = surname;
        this.personalIdentityNumber = personalIdentityNumber;

        generateClientID();
    }

    private void generateClientID() {
        random = new Random();
        for (int i = 0; i < 6 ; i++) {
            clientID += ""+ random.nextInt(10);
        }
    }

    public void addBankAccount(){}
    public void removeBankAccount(){}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public String getClientID() {
        return clientID;
    }

    public List<String> getClientAccountsIDList() {
        return clientAccountsIDList;
    }
}
