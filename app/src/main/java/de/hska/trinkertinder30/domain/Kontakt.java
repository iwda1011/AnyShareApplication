package de.hska.trinkertinder30.domain;

/**
 * Domain-Klasse für alle Kontakte
 *
 * @Version 1.0
 */
public class Kontakt {

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
