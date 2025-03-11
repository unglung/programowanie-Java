package bank;

class BankAccount {
    private String owner;
    private double balance;

    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    double getBalance() { // package-private
        return balance;
    }
}
