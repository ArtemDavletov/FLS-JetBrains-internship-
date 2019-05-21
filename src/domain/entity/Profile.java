package domain.entity;

public class Profile {
    private String name;
    private String password;
    private String DateOfLastCon;

    public Profile(String name, String password, String dateOfLastCon) {
    }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getDateOfLastCon() { return DateOfLastCon; }
}
