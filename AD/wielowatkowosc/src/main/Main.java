package main;

public class Main {

    public static void main(String[] args) {

        LoginView view = new LoginView();
        UserModel model = new UserModel();

        new LoginController(model, view);

        view.setVisible(true);
    }
}