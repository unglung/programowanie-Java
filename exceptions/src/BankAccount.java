public class BankAccount {
    private double saldo;

    public void wplata(double kwota) {
        if (kwota <= 0) {
            throw new IllegalArgumentException("Kwota wpłaty musi być większa od zera.");
        }
        saldo += kwota;
    }

    public void wyplata(double kwota) {
        if (kwota <= 0) {
            throw new IllegalArgumentException("Kwota wypłaty musi być większa od zera.");
        }
        if (kwota > saldo) {
            throw new IllegalStateException("Brak wystarczających środków na koncie.");
        }
        saldo -= kwota;
    }

    public double getSaldo() {
        return saldo;
    }
}
