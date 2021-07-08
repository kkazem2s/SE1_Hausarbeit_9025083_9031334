package model;

import java.io.Serializable;

public class UserStory implements Comparable<UserStory>, Serializable {
	private String titel;
	private int id;
	private int aufwand;
	private int mehrwert;
	private int risiko;
	private int strafe;
	private double prio;
	private Status status;
	private Akteur hauptakteur;
	private String s_mehrwert;
	private String s_risiko;
	private int rating;

	public UserStory(String titel, int id, int aufwand, int mehrwert, int risiko, int strafe, String s_mehrwert, String s_risiko) {
		this.titel = titel;
		this.id = id;
		this.aufwand = aufwand;
		this.mehrwert = mehrwert;
		this.risiko = risiko;
		this.strafe = strafe;
		this.prio = ((mehrwert + strafe ) / (aufwand + risiko)); //Formel nach Gloger

		this.hauptakteur = null;
		this.s_mehrwert = s_mehrwert;
		this.s_risiko = s_risiko;
		status = Status.TODO;
	}

	public Integer getID() {
		return id;
	}
	public String getTitel() {
		return titel;
	}
	public int getAufwand() {
		return aufwand;
	}
	public int getMehrwert() {
		return mehrwert;
	}
	public int getRisiko() {
		return risiko;
	}
	public int getStrafe() {
		return strafe;
	}
	public double getPrio() {
		return prio;
	}
	public Status getStatus() { return status; }

	public void setStatus(Status status) {
		this.status = status;
	}

	public String toString() {
		return "Titel: " + getTitel() + " | Aufwand: " + getAufwand()  + " | ID: " + getID()
			    + " | Mehrwert: " + getMehrwert() + " | Risiko: " + getRisiko() + " | Strafe: "
			    + getStrafe() + " | Priorität: " + getPrio() + " | Status: " + getStatus() + " | Hauptakteur: " + has_hauptakteur()
				+ "\nSchriftlicher Mehrwert: " + getS_mehrwert() + "\nSchriftliches Risiko: " + getS_risiko() + "\n";
	}

	public int compareTo(UserStory us) {
		if (us.getPrio() == this.getPrio() ) {
			return 0;
		}

		if (us.getPrio() > this.getPrio()) {
			return 1;
		}
		else return -1;
	}
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Akteur getHauptakteur() {
		return hauptakteur;
	}

	public void setHauptakteur(Akteur hauptakteur) {
		this.hauptakteur = hauptakteur;
	}

	public String getS_mehrwert() {
		return s_mehrwert;
	}

	public void setS_mehrwert(String s_mehrwert) {
		this.s_mehrwert = s_mehrwert;
	}

	public String getS_risiko() {
		return s_risiko;
	}

	public void setS_risiko(String s_risiko) {
		this.s_risiko = s_risiko;
	}

	public String has_hauptakteur() {
		if (this.hauptakteur == null) {
			return "NO ACTOR!";
		}
		return hauptakteur.getName();
	}
	public boolean has_hauptakteur_bol() {
		if (this.hauptakteur == null) {
			return false;
		}
		return true;
	}
}
/**
 * @author kkazem2s	- 9031334 
 * 		   mschub2s - 9025083
 */