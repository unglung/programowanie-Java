
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Main extends JFrame {

    private JPanel panelGlowny;
    private JTextField txtImie;
    private JTextField txtNazwisko;
    private JTextField txtStanowisko;
    private JButton btnDodaj;
    private JTable tabelaPracownikow;

    private DefaultTableModel model;

    private static final String URL = "jdbc:sqlite:pracownicy.db";

    public Main() {

        setContentPane(panelGlowny);

        setTitle("INF.04 - System Obsługi Pracowników");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
                new String[]{"ID", "Imię", "Nazwisko", "Stanowisko"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaPracownikow.setModel(model);

        utworzBazeDanych();

        btnDodaj.addActionListener(e -> dodajPracownika());

        odswiezTabele();
    }

    private void utworzBazeDanych() {

        String sql = """
                CREATE TABLE IF NOT EXISTS pracownicy (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    imie TEXT NOT NULL,
                    nazwisko TEXT NOT NULL,
                    stanowisko TEXT NOT NULL
                )
                """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Błąd bazy danych: " + e.getMessage()
            );
        }
    }

    private void odswiezTabele() {

        model.setRowCount(0);

        String sql = "SELECT * FROM pracownicy";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("stanowisko")
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Błąd pobierania danych: " + e.getMessage()
            );
        }
    }

    private void dodajPracownika() {

        String imie = txtImie.getText().trim();
        String nazwisko = txtNazwisko.getText().trim();
        String stanowisko = txtStanowisko.getText().trim();

        if (imie.isEmpty() || nazwisko.isEmpty() ||
                stanowisko.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Wypełnij wszystkie pola!",
                    "Błąd",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql = """
                INSERT INTO pracownicy (imie, nazwisko, stanowisko)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, imie);
            stmt.setString(2, nazwisko);
            stmt.setString(3, stanowisko);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Pracownik został dodany!"
            );

            txtImie.setText("");
            txtNazwisko.setText("");
            txtStanowisko.setText("");

            odswiezTabele();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Błąd dodawania: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Main okno = new Main();
            okno.setVisible(true);

        });
    }
}
