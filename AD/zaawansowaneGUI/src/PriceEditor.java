import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.DecimalFormat;

class PriceEditor extends AbstractCellEditor implements TableCellEditor {

    private final JTextField field = new JTextField();
    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public Object getCellEditorValue() {
        try {
            return Double.parseDouble(field.getText().replace(',', '.'));
        } catch (Exception e) {
            return 0.00;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        field.setText(value != null ? df.format(value) : "0.00");
        return field;
    }

    @Override
    public boolean stopCellEditing() {
        try {
            double v = Double.parseDouble(field.getText().replace(',', '.'));
            field.setText(df.format(v));
        } catch (Exception ignored) {}
        return super.stopCellEditing();
    }
}