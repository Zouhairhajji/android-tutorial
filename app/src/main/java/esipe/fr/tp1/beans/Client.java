package esipe.fr.tp1.beans;

import java.util.Date;

/**
 * Created by zouhairhajji on 05/10/2017.
 */

public class Client {

    private String firstName;

    private String lastName;

    private boolean sexe;

    private String email;

    private Date bidrthday;

    private String niveau;

    public Client() {

    }

    public Client(String firstName, String lastName, boolean sexe) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bidrthday = new Date();
    }

    public Client(String firstName, String lastName, boolean sexe, String email, Date bidrthday, String niveau) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.email = email;
        this.bidrthday = bidrthday;
        this.niveau = niveau;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getBidrthday() {
        return bidrthday;
    }

    public void setBidrthday(Date bidrthday) {
        this.bidrthday = bidrthday;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Client";
    }
}
