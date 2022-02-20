package service;

import domain.Offer;
import domain.User;
import repository.OfferDBRepo;
import repository.OfferRepo;
import repository.SetRepo;
import repository.UserDBRepo;

import java.sql.SQLException;
import java.util.List;

public class Service {

    private UserDBRepo userRepo;
    private OfferDBRepo offerRepo;

    public Service(UserDBRepo userRepo, OfferDBRepo offerRepo) {
        this.userRepo = userRepo;
        this.offerRepo = offerRepo;
    }

    public boolean addUser(String firstName, String lastName, String userName, String passworld, double balance) throws SQLException {

        if (userRepo.getUserByUserName(userName)==null) {
            User u = new User(firstName, lastName, userName, passworld, balance);
            userRepo.addUser(u);
            return true;
        }
        else return false;
    }

    public void updateUser(int id, String firstName, String lastName, String userName, String passworld, double balance) throws SQLException {

        User u = new User(firstName, lastName, userName, passworld, balance);
        u.setId(id);
        userRepo.updateUser(u);
    }

    public void deleteUser(int id) throws SQLException {
        userRepo.deleteUser(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepo.getAll();
    }

    public User getUserById(int id) throws SQLException {
        return userRepo.getById(id);
    }





    public void addOffer(String title, String description, User creator, double price, String data) throws SQLException {

        Offer o = new Offer(title,description,creator,price,data);
        offerRepo.addOffer(o);
    }

    public boolean updateOffer(int id,String title, String description, User creator, double price, String data, User userCerere) throws SQLException {

        if (userCerere.getIsAdmin()==true||userCerere.getId()==offerRepo.getById(id).getCreator().getId()){
            Offer o = new Offer(title,description,creator,price,data);
            o.setId(id);
            offerRepo.updateOffer(o);
            return true;
        }
        else return false;
    }

    public boolean deleteOffer(int id, User userCerere) throws SQLException {
        if (userCerere.getIsAdmin()==true||userCerere.getId()==offerRepo.getById(id).getCreator().getId()){

            offerRepo.deleteOffer(id);
            return true;
        }
        else return false;


    }

    public List<Offer> getAllOfferts() throws SQLException {
        return offerRepo.getAll();
    }

    public Offer getOfferById(int id) throws SQLException {
        return offerRepo.getById(id);
    }

    public User login(String userName,String password) throws SQLException {
        User u = userRepo.checkUserPassword(userName,password);
        return u;

    }

    public boolean buyOffer(User curentUser,int idOffer) throws SQLException {
        Offer offer = offerRepo.getById(idOffer);
        if (curentUser.getBalance()>=offer.getPrice()){
            curentUser.setBalance(curentUser.getBalance()-offer.getPrice());
            offer.getCreator().setBalance(offer.getCreator().getBalance()+offer.getPrice());
            //userRepo.writeToFile();
            offerRepo.deleteOffer(idOffer);
            return true;
        }
        else return false;
    }


}

