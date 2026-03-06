package main;

import javax.swing.*;

public class LoginView extends JFrame {

    JTextField txtLogin;
    JPasswordField txtPassword;
    JButton btnLogin;
    JLabel lblStatus;

    public LoginView() {

        setTitle("Panel logowania");
        setSize(320,220);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblLogin = new JLabel("Użytkownik:");
        lblLogin.setBounds(30,25,100,25);

        txtLogin = new JTextField();
        txtLogin.setBounds(140,25,130,25);

        JLabel lblHaslo = new JLabel("Hasło:");
        lblHaslo.setBounds(30,65,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140,65,130,25);

        btnLogin = new JButton("Zaloguj się");
        btnLogin.setBounds(95,105,120,30);

        lblStatus = new JLabel("Oczekiwanie...");
        lblStatus.setBounds(90,145,200,25);

        add(lblLogin);
        add(txtLogin);
        add(lblHaslo);
        add(txtPassword);
        add(btnLogin);
        add(lblStatus);
    }
}