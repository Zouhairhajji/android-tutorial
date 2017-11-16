package fr.esipe.ing3.android.clientapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by antho on 05/10/2017.
 */

public class Client {

    public Client() {

    }

    public String getId() {
        return id;
    }

    public enum Gender {
        MAN,WOMAN
    }
    private String id;
    private String lastname;
    private String firstname;
    private String email;
    private String level;
    private Gender gender;
    private boolean active;
    private Date birthDate;



    private static List<Client> clients;

    static {
        clients = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
         Client c =   new Client(String.valueOf(i),
                    "nom"+i,"prenom"+i,"email"+i,new Date(),
                    i %3 == 0 ? Gender.MAN : Gender.WOMAN,
                    true,
                    "Débutant"
            );
                    clients.add(c);
        }
    }

    public Client(String id,String lastname, String firstname, String email, Date birthDate,Gender gender, boolean active,String level) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
       this.email = email;
        this.level = level;
        this.gender = gender;
        this.active = active;
        this.birthDate = birthDate;
    }

    public static void setClient(Client c){
        clients.add(c);
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static void setClients(List<Client> clients) {
        Client.clients = clients;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

   public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }






    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
