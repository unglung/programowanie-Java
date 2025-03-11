package bank;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Jan Kowalski", 500000.00);
        BankManager manager = new BankManager();

        manager.printBalance(account);
    }
}
