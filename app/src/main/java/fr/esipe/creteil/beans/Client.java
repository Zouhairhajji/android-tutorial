package fr.esipe.creteil.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
        MAN, WOMAN
    }

    @SerializedName("id")
    private String id;

    @SerializedName("prenom")
    private String lastname;

    @SerializedName("nom")
    private String firstname;

    @SerializedName("email")
    private String email;


    //@Expose(serialize = false, deserialize = false)
    @SerializedName("sexe")
    private Gender gender;

    @Expose(serialize = false, deserialize = false)
    private Date birthDate;

    @SerializedName("niveau")
    private String level;

    @SerializedName("actif")
    private boolean active;

    private static List<Client> clients = new ArrayList<>();


    public Client(String id, String lastname, String firstname, String email, Date birthDate, Gender gender, boolean active, String level) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.level = level;
        this.gender = gender;
        this.active = active;
        this.birthDate = birthDate;
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


    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", level='" + level + '\'' +
                ", active=" + active +
                '}';
    }
}
