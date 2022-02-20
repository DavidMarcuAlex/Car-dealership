package repository;

import domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class SetRepo {

    private Set<User> set = new HashSet<>();
    private String numeFisier;

    public SetRepo(String numeFisier){
        this.numeFisier = numeFisier;
        this.readFromFile();
    }

    public void addUser(User a) {

        int max=0;

        for (User u:set
        ) {
            if (u.getId()>max)
                max=u.getId();

        }
        a.setId(max+1);
        set.add(a);
        writeToFile();
    }


    public void updateUser(User a){
        for (User u:set
        ) {
            if (u.getId()== a.getId()){
                u.setBalance(a.getBalance());
                u.setFirstName(a.getFirstName());
                u.setAdmin(a.getIsAdmin());
                u.setLastName(a.getLastName());
                u.setPassword(a.getPassword());
            }
        }
        writeToFile();
    }
    public void deleteUser(int id){
        User x=null;
        for (User u:set
        ) {
            if (id==u.getId()){
                x=u;

            }
        }
        System.out.println("da");
        System.out.println(set.remove(x));
        writeToFile();
    }
    public List<User> getAll(){
        List<User> list = new ArrayList();
        for (User u:set
        ) {
            list.add(u);
        }
        return list;
    }
    public User getById(int id){
        for (User u:set
        ) {
            if (id==u.getId()){
                return u;
            }
        }
        return null;
    }
    public User getUserByUserName(String userName){
        for (User u:set
        ) {
            if (u.getUserName().equals(userName)){
                return u;
            }
        }
        return null;
    }
    public User checkUserPassword(String userName, String password){

        if (getUserByUserName(userName).getPassword().equals(password)){
            return getUserByUserName(userName);
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
                set.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void writeToFile(){
        try{
            int i;
            FileWriter wr = new FileWriter(numeFisier);
            for (User u:set
            ) {
                wr.write(u.getId()+";"+u.getFirstName()+";"+u.getLastName()+";"+u.getUserName()+";"+u.getPassword()+";"+u.getBalance()+";"+u.getIsAdmin()+"\n");

            }


            wr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


