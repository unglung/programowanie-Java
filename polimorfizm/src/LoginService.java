public class LoginService {
    public boolean login(String username, String password) {
        System.out.println("Logging in using username and password");
        return true;
    }
    public boolean login(String login, String email , String password) {
        System.out.println("Logging in using username, email and password");
        return true;
    }
    public boolean login(String
                                 username, int token) {
        System.out.println("Logging in using username and token");
        return true;
    }
    public boolean login(int phoneNumber, String password) {
        System.out.println("Logging in using phone number and password");
        return true;
    }
}
