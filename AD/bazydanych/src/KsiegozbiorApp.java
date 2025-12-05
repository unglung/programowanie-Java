import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class KsiegozbiorApp extends JFrame {

    DefaultTableModel model = new DefaultTableModel(new String[]{"id","tytul","autor","rok"}, 0);
    JTable table = new JTable(model);
    JTextField tTytul = new JTextField(10);
    JTextField tAutor = new JTextField(10);
    JTextField tRok = new JTextField(5);

    String URL = "jdbc:mysql://localhost:3306/sklep";
    String USER = "root";
    String PASS = "";

    public KsiegozbiorApp() {
        setTitle("Mini CRUD");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel();
        top.add(tTytul);
        top.add(tAutor);
        top.add(tRok);
        add(top, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        JButton bAdd = new JButton("Dodaj");
        JButton bDel = new JButton("Usuń");
        JButton bUpd = new JButton("Aktualizuj");
        bottom.add(bAdd); bottom.add(bDel); bottom.add(bUpd);
        add(bottom, BorderLayout.SOUTH);

        bAdd.addActionListener(e -> add());
        bDel.addActionListener(e -> del());
        bUpd.addActionListener(e -> upd());

        load();
        setVisible(true);
    }

    Connection con() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    void load() {
        model.setRowCount(0);
        try (Connection c = con();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM ksiazki");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void add() {
        try (Connection c = con();
             PreparedStatement ps = c.prepareStatement("INSERT INTO ksiazki VALUES (NULL,?,?,?)")) {
            ps.setString(1, tTytul.getText());
            ps.setString(2, tAutor.getText());
            ps.setInt(3, Integer.parseInt(tRok.getText()));
            ps.executeUpdate();
            load();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void del() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) model.getValueAt(row,0);

        try (Connection c = con();
             PreparedStatement ps = c.prepareStatement("DELETE FROM ksiazki WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            load();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void upd() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) model.getValueAt(row,0);

        try (Connection c = con();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?")) {
            ps.setString(1, tTytul.getText());
            ps.setString(2, tAutor.getText());
            ps.setInt(3, Integer.parseInt(tRok.getText()));
            ps.setInt(4, id);
            ps.executeUpdate();
            load();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) {
        new KsiegozbiorApp();
    }
}
