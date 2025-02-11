public class Manager extends User {
    @Override
    String getAccessLevel() {
        return "Reports access";
    }
}
