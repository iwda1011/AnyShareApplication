package de.hska.trinkertinder30.domain;

/**
 * Domain-Klasse für alle Kategorien
 *
 * @Version 1.0
 */
public class Kategorie {

    String name, unterkategorie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnterkategorie() {
        return unterkategorie;
    }

    public void setUnterkategorie(String unterkategorie) {
        this.unterkategorie = unterkategorie;
    }
}
