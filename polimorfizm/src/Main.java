public class Main {
    public static void main(String[] args) {
        LoginService loginManager = new LoginService();
        loginManager.login("username123", "password123");
        loginManager.login("username123", "email@email.com", "password123");
        loginManager.login("username", 12345);
        loginManager.login(123123123, "password");

        Admin admin = new Admin();
        Programmer programmer = new Programmer();
        Tester tester = new Tester();
        Manager manager = new Manager();

        System.out.println(admin.getAccessLevel());
        System.out.println(programmer.getAccessLevel());
        System.out.println(tester.getAccessLevel());
        System.out.println(manager.getAccessLevel());
    }
}
