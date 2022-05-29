package sample;

import com.mysql.jdbc.Driver;
import sun.util.resources.LocaleData;

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

    public ResultSet  getUserID(String mail, String password){
        ResultSet resSet = null;

        String select = "SELECT " + Const.USER_ID + " FROM " + Const.USER_TABLE_LOGINS + " WHERE " + Const.USER_MAIL + "=? AND " +
                Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, mail);
            prSt.setString(2, password);
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
