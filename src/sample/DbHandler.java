package sample;

import com.mysql.jdbc.Driver;

import javax.xml.crypto.Data;
import java.sql.*;

public class DbHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void register(String Name, String gender, Date birth_date, String birth_plase,
                         String residence_address, String registration_address){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.FULL_NAME + "," + Const.BIRTH_DATE + "," + Const.GENDER +
                "," + Const.BIRTH_PLASE + "," + Const.RESIDENCE_ADDRESS +
                "," + Const.REGISTRATION_ADDRESS + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, Name);
            prSt.setDate(2, birth_date);
            prSt.setString(3, gender);
            prSt.setString(4, birth_plase);
            prSt.setString(5, residence_address);
            prSt.setString(6, registration_address);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void register1(int id_employee, String mail, String password, String role){
        String insert = "INSERT INTO " + Const.USER_TABLE_LOGINS + "(" + Const.USER_ID + "," +
                Const.USER_MAIL + "," + Const.USER_PASSWORD + "," + Const.USER_ROLE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, id_employee);
            prSt.setString(2, mail);
            prSt.setString(3, password);
            prSt.setString(4, role);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet  getUserID(String name, String gender, Date date, String birth_plase,
                                String residence_address, String registration_address){
        ResultSet resSet = null;
        String select = "SELECT id_employee FROM person WHERE full_name=? AND male=? AND birth_date =? AND birth_plase=? " +
                "AND residence_address =? AND registration_address=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, name);
            prSt.setString(2, gender);
            prSt.setDate(3, date);
            prSt.setString(4, birth_plase);
            prSt.setString(5, residence_address);
            prSt.setString(6, registration_address);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet  checkUser(String mail){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE_LOGINS + " WHERE " + Const.USER_MAIL + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, mail);
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

}
