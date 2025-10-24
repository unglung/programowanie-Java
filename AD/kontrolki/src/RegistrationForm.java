import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JFrame {

    public RegistrationForm() {
        super("Registration Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(300, 120);

        JTextField ageField = new JTextField(5);
        JCheckBox termsCheck = new JCheckBox("I accept the terms");
        JButton submitBtn = new JButton("Submit");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int age;
                try {
                    age = Integer.parseInt(ageField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid whole number for your age");
                    return;
                }

                if (age >= 18 && termsCheck.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You have been registered");
                } else {
                    JOptionPane.showMessageDialog(null, "You must be 18 or older and accept the terms");
                }
            }
        });

        this.add(new JLabel("Age:"));
        this.add(ageField);
        this.add(termsCheck);
        this.add(submitBtn);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
