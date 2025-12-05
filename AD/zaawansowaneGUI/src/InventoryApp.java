import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class InventoryApp {

    public InventoryApp() {
        JFrame frame = new JFrame("Menedżer Inwentarza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        JTabbedPane tabs = new JTabbedPane();

        JPanel productPanel = new JPanel(new BorderLayout());

        ProductTableModel model = new ProductTableModel();
        JTable table = new JTable(model);

        table.getColumnModel().getColumn(1).setCellRenderer(new LowStockRenderer());

        table.getColumnModel().getColumn(2).setCellEditor(new PriceEditor());

        JPanel controlPanel = new JPanel();
        JButton addBtn = new JButton("Dodaj Nowy Produkt");
        JButton removeBtn = new JButton("Usuń Wybrany");

        addBtn.addActionListener(e -> model.addProduct(new Product("Nowy", 1, 0.00, false)));
        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) model.removeProduct(row);
        });

        controlPanel.add(addBtn);
        controlPanel.add(removeBtn);

        productPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        productPanel.add(controlPanel, BorderLayout.SOUTH);

        JPanel reportPanel = new JPanel();
        reportPanel.add(new JLabel("Miejsce na przyszłe wykresy/statystyki"));

        tabs.addTab("Przegląd Produktów", productPanel);
        tabs.addTab("Raporty", reportPanel);

        frame.add(tabs);
        frame.setVisible(true);
    }

    void main(String[] args) {
        new InventoryApp();
    }
}

