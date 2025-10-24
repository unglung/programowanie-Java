import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JTextField nameField;
    private JPasswordField passField;
    private JButton loginButton;

    public Main() {
        super("Logowanie");

        nameField = new JTextField(20);
        passField = new JPasswordField(20);
        loginButton = new JButton("Zaloguj");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nazwa użytkownika:"));
        panel.add(nameField);
        panel.add(new JLabel("Hasło:"));
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String username = nameField.getText();
            String password = new String(passField.getPassword());
            System.out.println("Nazwa: " + username + ", Hasło: " + password);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
