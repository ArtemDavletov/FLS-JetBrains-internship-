package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import domain.entity.Profile;

public class SQLiteProfileDao implements DaoSQLite<Profile> {

    private static final String CREATE = "INSERT INTO profile (name, password) VALUES(?, ?)";
    private static final String AUTHORIZATION_PART_ONE = "SELECT password FROM profile WHERE name = ?";
    private static final String AUTHORIZATION_PART_TWO = "INSERT INTO profile (dateOfLastCon) values(?)";
    private static final String GET_ACTS = "SELECT act FROM action WHERE name = ?";
    private static final String CHANGE_PASSWORD = "UPDATE profile SET password = ? WHERE name = ?";
    private static final String ACTION = "INSERT INTO action (profile, date, act) VALUES(?, ?, ?)";
    private static final String GET_ALL_PROFILES = "SELECT * FROM profile";

    Connection con = ConnectionPool.getConnection();

    public SQLiteProfileDao() {
        /*
        try{
            Connection con = ConnectionPool.getConnection();
            this.psCreate = con.prepareStatement(CREATE);
            this.psAuthorizationPartOne = con.prepareStatement(AUTHORIZATION_PART_ONE);
            this.psAuthorizationPartTwo = con.prepareStatement(AUTHORIZATION_PART_TWO);
            this.psGetActs = con.prepareStatement(GET_ACTS);
            this.psChangePassword = con.prepareStatement(CHANGE_PASSWORD);
            this.psAction = con.prepareStatement(ACTION);
        } catch (Exception e) {
            throw new RuntimeException("Something bad");
        }
        */
    }
    /*
    private PreparedStatement psCreate;
    private PreparedStatement psAuthorizationPartOne;
    private PreparedStatement psAuthorizationPartTwo;
    private PreparedStatement psGetActs;
    private PreparedStatement psChangePassword;
    private PreparedStatement psAction;
    */

    @Override
    public void createProfile(String name, String password) {
        try {
                PreparedStatement psCreate;
                psCreate = con.prepareStatement(CREATE);
                psCreate.setString(1, name);
                psCreate.setString(2, password);

                psCreate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authorization(String name, String password, String dateOfLastCon) {
        try{
            PreparedStatement psAuthorizationPartOne;
            PreparedStatement psAuthorizationPartTwo;
            psAuthorizationPartOne = con.prepareStatement(AUTHORIZATION_PART_ONE);
            psAuthorizationPartTwo = con.prepareStatement(AUTHORIZATION_PART_TWO);

            psAuthorizationPartOne.setString(1, name);

            ResultSet resultSet = psAuthorizationPartOne.executeQuery();
            String sqlPassword = resultSet.getString("password");

            if(sqlPassword == password){
                psAuthorizationPartTwo.setString(1, dateOfLastCon);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getActs(String name) {
        try {
            PreparedStatement psGetActs;
            psGetActs = con.prepareStatement(GET_ACTS);

            psGetActs.setString(1, name);

            ResultSet resultSet = psGetActs.executeQuery();

            List<String> list = null;

            while(resultSet.next()) {
                list.add(resultSet.getString("act"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void changePassword(String name, String newPassword) {
        try{
            PreparedStatement psChangePassword;
            psChangePassword = con.prepareStatement(CHANGE_PASSWORD);

            psChangePassword.setString(1, newPassword);
            psChangePassword.setString(2, name);

            psChangePassword.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void action(String name, String date, String act) {
        try{
            PreparedStatement psAction;
            psAction = con.prepareStatement(ACTION);

            psAction.setString(1, name);
            psAction.setString(2, date);
            psAction.setString(3, act);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Profile> getAllProfiles() {
        try {
            PreparedStatement psGetAllProfiles;
            psGetAllProfiles = con.prepareStatement(GET_ALL_PROFILES);

            ResultSet resultSet = psGetAllProfiles.executeQuery();

            List<Profile> list = null;

            while(resultSet.next()) {
                list.add(new Profile(resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("dateOfLastCon")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
