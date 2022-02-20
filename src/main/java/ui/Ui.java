package ui;

import domain.User;
import service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Ui {


    private Service service;

    public Ui(Service service) {
        this.service = service;
    }

    public void meniu() throws IOException, SQLException {
        System.out.println("Apasa tasta 1 pt logare ");
        System.out.println("Apasa tasta 2 pt inregistrare ");
        System.out.println("Apasa tasta 0 pt inchiderea aplicatiei ");

        int tasta;
        String userName;
        String password;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        tasta = Integer.parseInt(reader.readLine());

        while (tasta != 0) {

            if (tasta == 1) {
                System.out.println("introdu username si parola");
                userName= reader.readLine();
                password= reader.readLine();
                User u = service.login(userName,password);
                if(u!=null){
                    if (u.getIsAdmin()==true){
                        consolaAdmin(u);
                    }
                    else consolaUser(u);
                }
                else System.out.println("username sau parola gresite ");
            }

            if (tasta == 2) {
                System.out.println("introdu firstName ");
                String firstName = reader.readLine();
                System.out.println("introdu lastName ");
                String lastName = reader.readLine();
                System.out.println("introdu username  ");
                userName = reader.readLine();
                System.out.println("introdu password ");
                password = reader.readLine();



                if(service.addUser(firstName,lastName,userName,password,0)==true){
                    System.out.println("Te ai inregistrat cu succes ");
                }
                else System.out.println("username ul este deja luat ");
            }
            if (tasta == 0) {
                System.out.println("ci@");
            }
            System.out.println("introdu o noua tasta ");
            tasta = Integer.parseInt(reader.readLine());

        }


    }

    public void consolaUser(User utilizatorCurent) throws IOException, SQLException {
        int tasta;
        String firstName;
        String lastName;
        String userName;
        String password;
        System.out.println("Apasa tasta 1 pt a sterge contul ");
        System.out.println("Apasa tasta 2 pt a modifica datele contului ");
        System.out.println("Apasa tasta 3 pt a afisa useri");
        System.out.println("Apasa tasta 4 pt a afisa un user cu id ul dat");
        System.out.println("Apasa tasta 5 pt a accesa meniul de oferte ");
        System.out.println("Apasa tasta 0 pentru deconectare ");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        tasta = Integer.parseInt(reader.readLine());

        while (tasta != 0) {
            if (tasta == 1) {
                service.deleteUser(utilizatorCurent.getId());
                System.out.println("contul a fost sters cu succes ");
                break;
            }
            if (tasta == 2) {
                System.out.println("introdu firstName ");
                firstName = reader.readLine();
                System.out.println("introdu lastName ");
                lastName = reader.readLine();
                System.out.println("introdu password ");
                password = reader.readLine();
                service.updateUser(utilizatorCurent.getId(),firstName,lastName,utilizatorCurent.getUserName(),password,utilizatorCurent.getBalance());
                System.out.println("Datele au fost modificate cu succes ");
            }
            if (tasta == 3) {
                service.getAllUsers().forEach(System.out::println);
            }
            if (tasta == 4) {
                int id = Integer.parseInt(reader.readLine());
                System.out.println(service.getUserById(id));
            }
            if (tasta == 5) {
                meniuOffer(utilizatorCurent);
            }
            if (tasta == 0) {

            }

            System.out.println("introdu o noua tasta ");
            tasta = Integer.parseInt(reader.readLine());
        }

    }

    public void consolaAdmin(User curentUser) throws IOException, SQLException {
        int tasta;
        System.out.println("----------------");
        System.out.println("Apasa tasta 1 pt a adauga un user ");
        System.out.println("Apasa tasta 2 pt a sterge un user ");
        System.out.println("Apasa tasta 3 pt a modifica un user ");
        System.out.println("Apasa tasta 4 pt a afisa un user cu id ul dat");
        System.out.println("Apasa tasta 5 pt a afisa toti useri  ");
        System.out.println("Apasa tasta 6 pt accesa meniul de oferte  ");
        System.out.println("Apasa tasta 0 pt a inchide aplicatia ");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        tasta = Integer.parseInt(reader.readLine());

        String firstName,lastName,userName,password;
        int id;
        double balance;

        while (tasta != 0){

            if(tasta == 1){
                System.out.println("introdu firstName ");
                firstName = reader.readLine();
                System.out.println("introdu lastName ");
                lastName = reader.readLine();
                System.out.println("introdu username  ");
                userName = reader.readLine();
                System.out.println("introdu password ");
                password = reader.readLine();
                System.out.println("introdu balance ");
                balance=Double.parseDouble(reader.readLine());
                service.addUser(firstName,lastName,userName,password,balance);

            }

            if(tasta == 2){
                System.out.println("introdu id ");
                id = Integer.parseInt(reader.readLine());
                service.deleteUser(id);
            }

            if(tasta == 3){
                System.out.println("id ");
                id = Integer.parseInt(reader.readLine());
                System.out.println("introdu firstName ");
                firstName = reader.readLine();
                System.out.println("introdu lastName ");
                lastName = reader.readLine();
                System.out.println("introdu username  ");
                userName = reader.readLine();
                System.out.println("introdu password ");
                password = reader.readLine();
                System.out.println("introdu balance ");
                balance=Double.parseDouble(reader.readLine());
                service.updateUser(id,firstName,lastName,userName,password,balance);

            }

            if(tasta == 5){
                service.getAllUsers().forEach(System.out::println);
            }

            if (tasta == 4){
                System.out.println("introdu id ul ");
                id = Integer.parseInt(reader.readLine());
                System.out.println(service.getUserById(id));
            }

            if(tasta == 6){
                meniuOffer(curentUser);

            }

            System.out.println("introdu o noua tasta ");
            tasta = Integer.parseInt(reader.readLine());
        }

    }

    public void meniuOffer(User curetUser) throws IOException, SQLException {
        System.out.println("Apasa tasta 1 pt a adauga o oferta ");
        System.out.println("Apasa tasta 2 pt a sterge o oferta ");
        System.out.println("Apasa tasta 3 pt a modifica o oferta ");
        System.out.println("Apasa tasta 4 pt a vizualiza toate ofertele ");
        System.out.println("Apasa tasta 5 pt a vizualiza o oferta dupa id ");
        System.out.println("Apasa tasta 6 pt a cumpara ");
        System.out.println("Apasa tasta 0 pt inchiderea meniului ");

        int tasta;
        String title;
        String description;
        double price;
        int id;
        String data;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        tasta = Integer.parseInt(reader.readLine());

        while (tasta != 0) {

            if (tasta == 1) {
                System.out.println("introduceti titlul ");
                title= reader.readLine();
                System.out.println("introduceti descrierea ");
                description= reader.readLine();
                System.out.println("introduceti pretul ");
                price=Double.parseDouble(reader.readLine());
                System.out.println("introduceti data ");
                data= reader.readLine();
                service.addOffer(title,description, curetUser, price, data);
            }

            if (tasta == 2) {
                System.out.println("introduceti id-ul pentru stergere ");
                id=Integer.parseInt(reader.readLine());
                if(service.deleteOffer(id,curetUser)==true){
                    System.out.println("S-a sters cu succes ");
                }
                else System.out.println("nu poti sterge ");
            }
            if (tasta == 3) {
                System.out.println("introdueti id-ul pentru oferta ");
                id=Integer.parseInt(reader.readLine());
                System.out.println("introduceti titlul ");
                title= reader.readLine();
                System.out.println("introduceti descrierea ");
                description= reader.readLine();
                System.out.println("introduceti pretul ");
                price=Double.parseDouble(reader.readLine());
                System.out.println("introduceti data ");
                data= reader.readLine();
                if (service.updateOffer(id,title, description,curetUser, price,data,curetUser)==true){
                    System.out.println("s-a modificat cu succes ");
                }
                else System.out.println("nu ai dreptul de a modifica aceasta oferta");

            }
            if (tasta == 4) {
                System.out.println(service.getAllOfferts());

            }
            if (tasta == 5) {
                System.out.println("introduceti Id ul pentru vizualizare ");
                id=Integer.parseInt(reader.readLine());
                System.out.println(service.getOfferById(id));

            }
            if (tasta == 6) {
                System.out.println("introduceti id ul ofertei");
                id=Integer.parseInt(reader.readLine());
                if (service.buyOffer(curetUser,id)==true){
                    System.out.println("Ai cumparat cu succes obiectul ");
                }
                else System.out.println("Fonduri insuficiente");
            }
            if (tasta == 0) {
                System.out.println("ci@");
            }
            System.out.println("introdu o noua tasta ");
            tasta = Integer.parseInt(reader.readLine());

        }


    }
}



