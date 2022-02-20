package repository;

import domain.Offer;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDBRepo {
    private Connection conn;
    private UserDBRepo us;

    public OfferDBRepo(Connection conn,UserDBRepo us) {
        this.conn = conn;
        this.us = us;
    }

    public void addOffer(Offer a) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO offer(id, title, description, idUser, price, data)" +
                "VALUES(?,?,?,?,?,?)");

        int id=1;
        while (getById(id)!=null){
            id++;
        }
        statement.setInt(1,id);
        statement.setString(2,a.getTitle());
        statement.setString(3,a.getDescription());
        statement.setInt(4,a.getCreator().getId());
        statement.setDouble(5,a.getPrice());
        statement.setString(6,a.getData());

        int afffectedRows = statement.executeUpdate();

    }

    public void updateOffer(Offer a) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE offer SET title=?,description=?,price=?,data=?" +
                "WHERE id=?;");
        statement.setString(2,a.getTitle());
        statement.setString(3,a.getDescription());
        statement.setDouble(5,a.getPrice());
        statement.setString(6,a.getData());

        int afffectedRows = statement.executeUpdate();
    }

    public void deleteOffer(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM offer WHERE id=?");
        statement.setInt(1,id);
        int afffectedRows = statement.executeUpdate();
    }
    public List<Offer> getAll() throws SQLException {
        List<Offer> l = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT * from offer");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String title=resultSet.getString("title");
            String description=resultSet.getString("description");
            int idUser=resultSet.getInt("idUser");
            double price=resultSet.getDouble("price");
            String data=resultSet.getString("data");
            Offer o1 = new Offer(title,description,us.getById(idUser),price,data);
            o1.setId(id);
            l.add(o1);
        }
        return l;
    }


    public Offer getById(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * from offer where id= ? ");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        Offer o2 = null;
        while (resultSet.next()){
            String title=resultSet.getString("title");
            String description=resultSet.getString("description");
            int idUser=resultSet.getInt("idUser");
            double price=resultSet.getDouble("price");
            String data=resultSet.getString("data");
            o2 = new Offer(title,description,us.getById(idUser),price,data);
            o2.setId(id);


        }
        return o2;
    }



}
