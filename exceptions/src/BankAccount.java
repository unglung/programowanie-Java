public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("kwota wpłaty musi być większa od zera");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("kwota wypłaty musi być większa od zera");
        }
        if (amount > balance) {
            throw new IllegalStateException("brak wystarczających środków na koncie");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
