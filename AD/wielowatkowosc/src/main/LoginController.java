package main;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class LoginController {

    private UserModel userModel;
    private LoginView loginView;

    public LoginController(UserModel userModel, LoginView loginView) {
        this.userModel = userModel;
        this.loginView = loginView;

        loginView.btnLogin.addActionListener(e -> wykonajLogowanie());
    }

    private void wykonajLogowanie() {

        String login = loginView.txtLogin.getText();
        String haslo = new String(loginView.txtPassword.getPassword());

        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {

                loginView.btnLogin.setEnabled(false);
                loginView.lblStatus.setText("Sprawdzanie danych...");

                return userModel.walidujLogowanie(login, haslo);
            }

            @Override
            protected void done() {

                try {

                    boolean rezultat = get();

                    if(rezultat) {
                        loginView.lblStatus.setText("Zalogowano poprawnie");
                    }
                    else {
                        loginView.lblStatus.setText("Niepoprawne dane");
                    }

                } catch (InterruptedException | ExecutionException e) {
                    loginView.lblStatus.setText("Wystąpił błąd logowania");
                }

                loginView.btnLogin.setEnabled(true);
            }
        };

        worker.execute();
    }
}