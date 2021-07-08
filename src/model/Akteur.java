package model;

import java.io.Serializable;

public class Akteur implements Serializable {
    String name;
    int id;

    public Akteur(String name, int id) {
        this.name = name;
        this.id = id;
    }
    // Getter & Setter Methods
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return "Akteur: " + name + " (ID: " + id + ")";
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */