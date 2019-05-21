package domain;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import dal.DaoSQLite;
import dal.SQLiteProfileDao;
import domain.entity.Profile;

public class Manager {
    private final DaoSQLite<Profile> dao = new SQLiteProfileDao();

    public Manager() {
    }

    public void createProfile(String name, String password){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        dao.createProfile(name, password);
        dao.action(name, strDate, "profile-created");
    }

    public boolean authorization(String name, String password){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        dao.action(name, strDate, "login");
        return dao.authorization(name, password, strDate);
    }

    public List<String> getActs(String name){
        return dao.getActs(name);
    }

    public void changePassword(String name, String newPassword){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        dao.changePassword(name, newPassword);
        dao.action(name, strDate, "change-password");
    }

    public List<Profile> getAllProfiles(){
        return dao.getAllProfiles();
    }
}
