import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame {
    public FlowLayoutDemo() {
        super("FlowLayout Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);

        // 1. Ustawienie menadżera FlowLayout (jest domyślny dla JPanel, ale warto ustawić jawnie)
        setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5)); // Wyrównanie do środka, 10px odstępu

        // 2. Dodawanie komponentów
        add(new JButton("Przycisk 1"));
        add(new JButton("Przycisk Dwa"));
        add(new JButton("Przycisk Numer Trzy"));
        add(new JButton("Czwarty"));

        setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutDemo();
    }
}
    