package repository;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBRepo {

    private Connection conn;

    public UserDBRepo(Connection conn) {
        this.conn = conn;
    }

    public void addUser(User a) throws SQLException {


        PreparedStatement statement = conn.prepareStatement("INSERT INTO user(id,firstName," +
                "lastName,userName,password,balance,isAdmin) VALUES(?,?,?,?,?,?,?)");

        int id=1;
        while (getById(id)!=null){
            id++;
        }
        statement.setInt(1,id);
        statement.setString(2,a.getFirstName());
        statement.setString(3,a.getLastName());
        statement.setString(4,a.getUserName());
        statement.setString(5,a.getPassword());
        statement.setDouble(6,a.getBalance());
        statement.setBoolean(7,a.getIsAdmin());

        int afffectedRows = statement.executeUpdate();

    }


    public void updateUser(User a) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE user SET firstName=?,lastName=?," +
                " password=?,balance=?,isAdmin=? WHERE id=?;");
        statement.setString(1,a.getFirstName());
        statement.setString(2,a.getLastName());
        statement.setString(3,a.getPassword());
        statement.setDouble(4,a.getBalance());
        statement.setBoolean(5,a.getIsAdmin());
        statement.setInt(6,a.getId());

        int afffectedRows = statement.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("DELETE FROM user WHERE id=?");
        statement.setInt(1,id);
        int afffectedRows = statement.executeUpdate();
    }

    public List<User> getAll() throws SQLException {
        List<User> l = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT * from user");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String firstName=resultSet.getString("firstName");
            String lastName=resultSet.getString("lastName");
            String userName=resultSet.getString("userName");
            String password=resultSet.getString("password");
            double balance=resultSet.getDouble("balance");
            boolean isAdmin=resultSet.getBoolean("isAdmin");
            User u1 = new User(firstName,lastName,userName,password,balance,isAdmin);
            u1.setId(id);
            l.add(u1);
        }
        return l;
    }

    public User getById(int id) throws SQLException {
        
        PreparedStatement statement = conn.prepareStatement("SELECT * from user where id= ? ");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        User u1 = null;
        while (resultSet.next()){
            int id1=resultSet.getInt("id");
            String firstName=resultSet.getString("firstName");
            String lastName=resultSet.getString("lastName");
            String userName=resultSet.getString("userName");
            String password=resultSet.getString("password");
            double balance=resultSet.getDouble("balance");
            boolean isAdmin=resultSet.getBoolean("isAdmin");
            u1 = new User(firstName,lastName,userName,password,balance,isAdmin);
            u1.setId(id);

        }
        return u1;
    }

    public User getUserByUserName(String userName) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * from user where userName= ? ");
        statement.setString(1,userName);
        ResultSet resultSet = statement.executeQuery();
        User u1 = null;
        while (resultSet.next()){
            int id1=resultSet.getInt("id");
            String firstName=resultSet.getString("firstName");
            String lastName=resultSet.getString("lastName");
            String userName1=resultSet.getString("userName");
            String password=resultSet.getString("password");
            double balance=resultSet.getDouble("balance");
            boolean isAdmin=resultSet.getBoolean("isAdmin");
            u1 = new User(firstName,lastName,userName1,password,balance,isAdmin);
            u1.setId(id1);

        }
        return u1;

    }

    public User checkUserPassword(String userName, String password) throws SQLException {
        System.out.println(userName);
        PreparedStatement statement = conn.prepareStatement("SELECT * from user where userName = ? ");
        statement.setString(1,userName);
        ResultSet resultSet = statement.executeQuery();
        User u1 = null;
        while (resultSet.next()){
            System.out.println("da");
            int id1=resultSet.getInt("id");
            String firstName=resultSet.getString("firstName");
            String lastName=resultSet.getString("lastName");
            String userName1=resultSet.getString("userName");
            String password1=resultSet.getString("password");
            double balance=resultSet.getDouble("balance");
            boolean isAdmin=resultSet.getBoolean("isAdmin");
            u1 = new User(firstName,lastName,userName1,password1,balance,isAdmin);
            u1.setId(id1);

        }
        if (password.equals(u1.getPassword())) {
            return u1;
        }
        else return null;

    }


}
