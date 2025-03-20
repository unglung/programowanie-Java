public class Main {
    public static void main(String[] args) {
        BankAccount konto = new BankAccount();

        try {
            konto.wplata(100);
            System.out.println("Saldo po wpłacie: " + konto.getSaldo());

            konto.wyplata(50);
            System.out.println("Saldo po wypłacie: " + konto.getSaldo());

            konto.wyplata(100);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
