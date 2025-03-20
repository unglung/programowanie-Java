public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        try {
            account.deposit(100);
            System.out.println("Saldo po wpłacie: " + account.getBalance());

            account.withdraw(50);
            System.out.println("Saldo po wypłacie: " + account.getBalance());

            // sprawdzanie błedów
            account.withdraw(-100);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
