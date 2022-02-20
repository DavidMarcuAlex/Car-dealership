package repository;

import domain.Offer;
import domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OfferRepo {
    private UserDBRepo userRepo;
    List<Offer> listOffer = new ArrayList<>();
    String numeFisier;

    public OfferRepo(String s, UserDBRepo userRepo) {
        this.userRepo=userRepo;
        numeFisier=s;
        readFromFile();
    }

    public void addOffer(Offer a){
        int max=0;
        for (int i=0;i<listOffer.size();i++)
        {
            if (listOffer.get(i).getId()>max)
                max=listOffer.get(i).getId();
        }
        max++;

        a.setId(max);
        listOffer.add(a);
        writeToFile();
    }

    public void updateOffer(Offer a){
        for (int i=0;i<listOffer.size();i++){
            if(listOffer.get(i).getId()==a.getId()){
                listOffer.remove(i);
                listOffer.add(a);
                break;
            }

        }
        writeToFile();
    }

    public void deleteOffer(int id){
        if (listOffer.size()>0) {
            for (int i = 0; i < listOffer.size(); i++) {
                if (listOffer.get(i).getId() == id) {
                    listOffer.remove(i);
                    break;
                }
            } writeToFile();
        }
        else System.out.println("Nu mai sunt oferte de sters ");

    }

    public List<Offer> getAll(){
        return listOffer;
    }

    public Offer getById(int id){
        for (int i=0;i<listOffer.size();i++)
        {
            if (listOffer.get(i).getId()==id){
                return listOffer.get(i);
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
                User u=userRepo.getById(Integer.parseInt(values.get(3)));
                Offer offer = new Offer(values.get(1),values.get(2),u,Double.parseDouble(values.get(4)),values.get(5));
                offer.setId(Integer.parseInt(values.get(0)));
                listOffer.add(offer);
            }
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){

        try{
            int i;
            FileWriter wr = new FileWriter(numeFisier);
            for (i=0;i<listOffer.size();i++){
                wr.write(listOffer.get(i).getId()+";"+listOffer.get(i).getTitle()+";"+listOffer.get(i).getDescription()+";"+listOffer.get(i).getCreator().getId()+";"+listOffer.get(i).getPrice()+";"+listOffer.get(i).getData()+"\n");

            }
            wr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

