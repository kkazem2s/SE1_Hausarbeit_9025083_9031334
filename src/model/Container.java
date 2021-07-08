package model;

import control.MyConsole;
import exceptions.ContainerException;

import java.io.*;
import java.util.*;

/*
 * Klasse zum Abspeichern von User Stories in einer Liste
 * Diese Klasse repräsentiert laut MVC-Pattern das Model
 *
 * c/o Sascha Alda, H-BRS, 2015
 *
 */

public class Container {
	private static Container instance = new Container();
	private List<UserStory> list = null;
	private List<Akteur> akteure = null;
	private Stack<String> commands = new Stack<>();
	private Stack<Integer> ids = new Stack<>();
	public final static String LOCATION = "/Users/Public/Documents/userstories.ser";
	public final static String LOCATION_A = "/Users/Public/Documents/akteure.ser";

	private Container() {
		list = new ArrayList<UserStory>();
		akteure = new ArrayList<Akteur>();
	}
	public static synchronized Container getInstance() {
		if (Container.instance == null) {
			Container.instance = new Container();
		}
		return Container.instance;
	}
	public void addUS(UserStory us) {
		try {
			if (contains(us)) {
				throw new ContainerException();
			}
			list.add(us);
			System.out.println("User Story mit ID: " + us.getID() + " wurde im System registriert! (Prio: " + us.getPrio() + ")");
			commands.push("userstory");
			ids.push(us.getID());
		} catch (Exception e) {
			System.out.println("ID bereits vergeben!");
		}
	}

	private boolean contains(UserStory us) {
		for (UserStory usLoaded : list) {
			if (usLoaded.getID() == us.getID()) {
				return true;
			}
		}
		return false;
	}

	public List<UserStory> getCurrentList() {
		return this.list;
	}
	public List<Akteur> getCurrentListAkteur() {
		return this.akteure;
	}

	public UserStory getUserStory(int id) {
		for (UserStory usLoaded : list) {
			if (id == usLoaded.getID()) {
				return usLoaded;
			}
		}
		return null;
	}

	// Commands der Container-Class

	public void enter() {
		MyConsole console = new MyConsole();

		System.out.println("Geben sie die Grunddaten der User Story ein:");

		int id = console.readLineInt("ID der UserStory: ");
		boolean exist = false;
		for (UserStory us : list) {
			if (id == us.getID()) {
				exist = true;
			}
		}
		if (exist) {
			System.out.println("Diese ID ist bereits vergeben. Bitte wählen sie eine andere ID für ihre UserStory.");
			enter();
		} else {
			String titel = console.readLine("Titel der UserStory: ");
			int aufwand = console.readLineInt("Aufwand der UserStory: ");
			int risk = console.readLineInt("Risiko der UserStory: ");
			int mehrwert = console.readLineInt("Mehrwert der UserStory: ");
			int strafe = console.readLineInt("Strafe der UserStory: ");
			//double prio =  ((mehrwert + strafe ) / (aufwand + risk));

			if (id < 1 || aufwand < 1 || risk < 1 || mehrwert < 1 || strafe < 1) {
				System.out.println("\nZahlen kleiner als 1 sind leider ungültig!\nBitte neue Eingabe:\n");
				enter();
			} else {
				String s_mehrwert = (console.readLine("Schriftlich festgehaltener Mehrwert der UserStory: "));
				String s_risiko = (console.readLine("Schriftlich festgehaltenes Risiko der UserStory: "));

				UserStory us = new UserStory(titel, id, aufwand, mehrwert, risk, strafe, s_mehrwert, s_risiko);
				//System.out.println("User Story mit ID: " + id + " hat die Prio: " + us.getPrio());

				Container.getInstance().addUS(us);
			}
		}
	}
	public void dump() {
		if (list.isEmpty()) {
			System.out.println("Keine UserStories vorhanden!");
		} else {
			for (UserStory us : list) {
				System.out.println(us.toString());
			}
		}
	}
	public void dump_actor(){
		if (Container.getInstance().getAnzahl_Akteure() == 0){
			System.out.println("Keine Akteure vorhanden!");
		}else{
			for (Akteur ar : akteure){
				System.out.println(ar.toString());
			}
		}
	}

	public boolean usExist(int id) {
		for (UserStory us : list) {
			if (us.getID() == id) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 *
	 */
	public void store() {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null; //NEU

		ObjectOutputStream oos2 = null;
		FileOutputStream fos2 = null; //NEU

		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);

			fos2 = new FileOutputStream(Container.LOCATION_A);
			oos2 = new ObjectOutputStream(fos2);

			try {
				oos.writeObject(list);
				oos2.writeObject(akteure); //NEU
				System.out.println(list.size() + " UserStories und " + akteure.size() + " Akteure wurden erfolgreich gespeichert!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (oos != null) try { oos.close(); } catch (IOException e) {e.printStackTrace();}
			if (oos2 != null) try { oos2.close(); } catch (IOException e) {e.printStackTrace();}
		}

	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * Die geladene Liste überschreibt aktuell die vorhandenen Objekte
	 * in der Liste --> Optimierung notwendig ;-!
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;

		ObjectInputStream ois2 = null;
		FileInputStream fis2 = null;

		try {
			fis = new FileInputStream(Container.LOCATION);
			ois = new ObjectInputStream(fis);

			fis2 = new FileInputStream(Container.LOCATION_A); //NEU
			ois2 = new ObjectInputStream(fis2); //NEU

			// Auslesen der Liste
			Object obj = ois.readObject();
			Object obj2 = ois2.readObject();
			if (obj instanceof List<?>) {
				this.list = (List) obj;
			}
			if (obj2 instanceof List<?>) { //NEU
				this.akteure = (List) obj2; //NEU
			} //NEU

			System.out.println("Es wurden " + list.size() + " UserStories und " + akteure.size() + " Akteure erfolgreich geladen!");
		}
		catch (IOException e) {
			System.out.println("FEHLER: Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("FEHLER: Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
			if (ois != null) try { ois.close(); } catch (IOException e) {e.printStackTrace();}
			if (fis != null) try { fis.close(); } catch (IOException e) {e.printStackTrace();}
			if (ois2 != null) try { ois2.close(); } catch (IOException e) {e.printStackTrace();}
			if (fis2 != null) try { fis2.close(); } catch (IOException e) {e.printStackTrace();}
		}
	}
	public void undo() {
		if (commands.empty() && ids.empty()) {
			System.out.println("Nothing to undo!");
		} else {
			String name = commands.pop();
			int id = ids.pop();

			if (name.equals("actor")) {
				String a_name = "";
				for (int i = 0; i < akteure.size(); i++) {
					if (akteure.get(i).getID() == id - 1) {
						//System.out.println("Der Akteur '"+ akteure.get(i).getName() + "' mit der ID '" + i + "' wurde wieder entfernt.");
						System.out.println("Der Akteur '"+ akteure.get(i).getName() + "' wurde wieder entfernt.");
						a_name = akteure.get(i).getName();
						akteure.remove(i);

					}
				}
				try {
					for (UserStory us : list) {
						if (us.getHauptakteur().getName().equals(a_name)) {
							us.setHauptakteur(null);
						}
					}
				} catch (Exception e) {
					System.out.print("");
				}
			} else if (name.equals("userstory")) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getID() == id) {
						list.remove(i);
						System.out.println("Die UserStory mit der ID '" + id + "' wurde wieder entfernt.");
					}
				}
			} else {
				System.out.println("Nothing to undo!");
			}
		}
	}

	// Akteure
	public void addAkteur(String name) {
		boolean exist = checkAkteurexist(name);

		if (!exist) {
			Akteur a = new Akteur(name, akteure.size() );
			akteure.add(a);
			System.out.println("Der Akteur '" + a.getName() +"' wurde im System registriert!");
			commands.push("actor");
			ids.push(akteure.size());
		} else {
			System.out.println("Ein Akteur mit diesem Namen ist bereits vorhanden!");
		}
	}
	private boolean checkAkteurexist(String name) {
		for (Akteur akteur : akteure) {
			if (name.equals(akteur.getName())) {
				return true;
			}
		}
		return false;
	}
	public void deleteAkteur(Akteur akteur) {
		akteure.remove(akteur);
	}

	public String getAkteur(int id) {
		for (Akteur akteur : akteure) {
			if (id == akteur.getID()) {
				return akteur.getName();
			}
		}
		return null;
	}
	public Akteur getRealAkteur(int id) {
		for (Akteur akteur : akteure) {
			if (id == akteur.getID()) {
				return akteur;
			}
		}
		return null;
	}
	public int getAnzahl_Akteure() {
		if (akteure == null) {
			return 0;
		}
		return akteure.size();
	}
	public boolean exists(String str, int n) {
		switch(str) {
			case "aufwand":
				for (UserStory us : list) {
					if (us.getAufwand() >= n) {
						return true;
					}
				}
				return false;
			case "id":
				for (UserStory us : list) {
					if (us.getID() >= n) {
						return true;
					}
				}
				return false;
			case "mehrwert":
				for (UserStory us : list) {
					if (us.getMehrwert() >= n) {
						return true;
					}
				}
				return false;
			case "risiko":
				for (UserStory us : list) {
					if (us.getRisiko() >= n) {
						return true;
					}
				}
				return false;
			case "strafe":
				for (UserStory us : list) {
					if (us.getStrafe() >= n) {
						return true;
					}
				}
				return false;
			case "prio":
				for (UserStory us : list) {
					if (us.getPrio() >= n) {
						return true;
					}
				}
				return false;
			default:
				return false;
		}
	}
	public boolean exist(String str) {
		if (list.size() == 0) {
			return false;
		} else {
			switch(str) {
				case "todo":
					for (UserStory us : list) {
						if (us.getStatus().equals(Status.TODO)) {
							return true;
						}
					}
					return false;
				case "progress":
					for (UserStory us : list) {
						if (us.getStatus().equals(Status.PROGRESS)) {
							return true;
						}
					}
					return false;
				case "done":
					for (UserStory us : list) {
						if (us.getStatus().equals(Status.DONE)) {
							return true;
						}
					}
					return false;
				default:
					return false;
			}
		}
	}
	/**
	 * FOR TESTING PURPOSES
	 */
	public static Container TEST_CreateInstance() {
		return new Container();
	}
}
/**
 * @author kkazem2s	- 9031334 
 * 		   mschub2s - 9025083
 */