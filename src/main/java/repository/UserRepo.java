package repository;

import domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserRepo {

    List<User> userList = new ArrayList<>();
    private String numeFisier;

    public UserRepo(String numeFisier) {

        this.numeFisier=numeFisier;
        readFromFile();
    }

    public void addUser(User a){
        if(getUserByUserName(a.getUserName())==null) {
            int max = 0;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getId() > max)
                    max = userList.get(i).getId();
            }
            max++;

            a.setId(max);
            userList.add(a);
        }
        writeToFile();

    }

    public void updateUser(User a){

        for (int i=0;i<userList.size();i++){
            if(userList.get(i).getId()==a.getId()){
                userList.remove(i);
                userList.add(a);
                break;
            }

        }

        writeToFile();
    }

    public void deleteUser(int id){
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getId()==id){
                userList.remove(i);
                break;
            }
        }
        writeToFile();
    }

    public List<User> getAll(){
        return userList;
    }

    public User getById(int id){
        for (int i=0;i<userList.size();i++)
        {
            if (userList.get(i).getId()==id){
                return userList.get(i);
            }
        }
        return null;
    }



    private void readFromFile(){

        try {
            File ob = new File(numeFisier);
            Scanner scanner = new Scanner(ob);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                List<String> values = Arrays.asList(line.split(";"));
                User user = new User(values.get(1),values.get(2),values.get(3),values.get(4),Double.parseDouble(values.get(5)),Boolean.parseBoolean(values.get(6)));
                user.setId(Integer.parseInt(values.get(0)));
                userList.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(){

        try{
            int i;
            FileWriter wr = new FileWriter(numeFisier);
            for (i=0;i<userList.size();i++){
                wr.write(userList.get(i).getId()+";"+userList.get(i).getFirstName()+";"+userList.get(i).getLastName()+";"+userList.get(i).getUserName()+";"+userList.get(i).getPassword()+";"+userList.get(i).getBalance()+";"+userList.get(i).getIsAdmin()+"\n");

            }
            wr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByUserName(String userName){
        for (int i=0;i<userList.size();i++){
            if (userList.get(i).getUserName().equals(userName)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public User checkUserPassword(String userName, String password){
        User u = getUserByUserName(userName);

        if (u!=null && u.getPassword().equals(password))
            return u;
        else return null;
    }

}

