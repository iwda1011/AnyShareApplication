package de.hska.trinkertinder30.domain;

/**
 * Domain-Klasse für alle Veranstaltungen
 *
 * @Version 1.0
 */
public class Veranstaltung {

    String beschreibung, detail, kategorie, veranstalter, timestamp;

    public Veranstaltung(){}

    public Veranstaltung(String beschreibung, String detail, String kategorie) {
        this.beschreibung = beschreibung;
        this.detail = detail;
        this.kategorie = kategorie;
    }

    public Veranstaltung(String beschreibung, String detail,String veranstalter,String kategorie) {
        this.beschreibung = beschreibung;
        this.detail = detail;
        this.kategorie = kategorie;
        this.veranstalter = veranstalter;
    }

    public Veranstaltung(String beschreibung, String detail,String veranstalter,String kategorie, String timestamp) {
        this.beschreibung = beschreibung;
        this.detail = detail;
        this.kategorie = kategorie;
        this.veranstalter = veranstalter;
        this.timestamp = timestamp;
    }

    public String getVeranstalter() {
        return veranstalter;
    }

    public void setVeranstalter(String veranstalter) {
        this.veranstalter = veranstalter;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    @Override
    public String toString() {
        return kategorie  + " > " + beschreibung + " mit " + veranstalter +"\n"+ timestamp ;
    }
}
