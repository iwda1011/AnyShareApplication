package de.hska.trinkertinder30.domain;

/**
 * Created by davidiwertowski on 16.12.16.
 */

public class Kontakt {

    /**
     * Hier wird die Variable für Name, Emailadresse, Username und Passwort gesetzt
     */
    String name, vorname, email, pass;
    static String uname;

    public Kontakt(){}

    public Kontakt(String name, String vorname, String email, String pass){
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
}
