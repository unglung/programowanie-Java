import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class ProductTableModel extends AbstractTableModel {

    private final String[] columns = {"Nazwa", "Ilość", "Cena", "Czy Kruchliwy"};
    private final List<Product> products = new ArrayList<>();

    public ProductTableModel() {
        products.add(new Product("Jabłka", 10, 2.50, false));
        products.add(new Product("Szkło", 3, 12.00, true));
    }

    public void addProduct(Product p) {
        products.add(p);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    public void removeProduct(int row) {
        products.remove(row);
        fireTableRowsDeleted(row, row);
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Integer.class;
            case 2 -> Double.class;
            case 3 -> Boolean.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return switch (col) {
            case 0, 2, 3 -> true;
            case 1 -> true;
            default -> false;
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Product p = products.get(row);
        return switch (col) {
            case 0 -> p.name;
            case 1 -> p.quantity;
            case 2 -> p.price;
            case 3 -> p.fragile;
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Product p = products.get(row);

        switch (col) {
            case 0 -> p.name = (String) value;
            case 1 -> p.quantity = (Integer) value;
            case 2 -> p.price = (Double) value;
            case 3 -> p.fragile = (Boolean) value;
        }
        fireTableCellUpdated(row, col);
    }
}