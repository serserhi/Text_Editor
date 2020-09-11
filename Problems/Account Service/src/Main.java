interface AccountService {
    /**
     * It finds an account by owner id
     * @param id owner unique identifier
     * @return account or null
     */
    Account findAccountByOwnerId(long id);
    /**
     * It count the number of account with balance > the given value
     * @param value
     * @return the number of accounts
     */
    long countAccountsWithBalanceGreaterThan(long value);
}

// Declare and implement your AccountServiceImpl here

class AccountServiceImpl implements AccountService {
    Account[] cuentas;
    public AccountServiceImpl(Account[] cuentas){
        this.cuentas = cuentas;
    }
    public Account findAccountByOwnerId(long id) {
        int aux = 0;
        for (int i = 0; i<= cuentas.length - 1; i++) {
            if (cuentas[i].getOwner().getId() == id ) {
                aux = i;
            }
        }
        if (aux == 0) {
            return null;
        } else {
            return cuentas[aux];
        }

    }
    public  long countAccountsWithBalanceGreaterThan(long value){
        int aux = 0;
        for (int i = 0; i <= cuentas.length -1; i++) {
            if (cuentas[i].getBalance() > value ) {
                aux ++;
            }
        }
        return aux;
    }
}

class Account {

    private long id;
    private long balance;
    private User owner;

    public Account(long id, long balance, User owner) {
        this.id = id;
        this.balance = balance;
        this.owner = owner;
    }

    public long getId() { 
        return id; 
    }

    public long getBalance() { 
        return balance; 
    }

    public User getOwner() { 
        return owner; 
    }
}

class User {

    private long id;
    private String firstName;
    private String lastName;

    public User(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() { 
        return id; 
    }

    public String getFirstName() { 
        return firstName; 
    }

    public String getLastName() { 
        return lastName; 
    }
}