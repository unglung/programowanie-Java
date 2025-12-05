import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class LowStockRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        super.setValue(value);
        if (value instanceof Integer quantity) {
            if (quantity <= 5) {
                setBackground(new Color(255, 200, 100));
            } else {
                setBackground(Color.WHITE);
            }
        }
    }
}