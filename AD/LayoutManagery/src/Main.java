import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Logowanie Użytkownika");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Panel Logowania");
        topPanel.add(titleLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Zaloguj");
        JButton cancelButton = new JButton("Anuluj");
        bottomPanel.add(loginButton);
        bottomPanel.add(cancelButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        JTextArea textArea = new JTextArea();
        frame.add(textArea, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
