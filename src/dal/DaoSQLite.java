package dal;

import java.util.List;

public interface DaoSQLite<T> {

    public void createProfile(String name, String password);

    public boolean authorization(String name, String password, String dateOfLastCon);

    List<String> getActs(String name);

    public void changePassword(String name, String newPassword);

    public void action(String name, String date, String act);

    List<T> getAllProfiles();

}
