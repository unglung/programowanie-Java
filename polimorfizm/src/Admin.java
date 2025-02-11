class Admin extends User {
    @Override
    String getAccessLevel() {
        return "Full access";
    }
}
