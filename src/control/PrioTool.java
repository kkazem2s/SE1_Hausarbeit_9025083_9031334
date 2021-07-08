package control;

import commands.*;
import commands.analyze.Analyze_all;
import commands.analyze.Analyze_single;
import model.Akteur;
import model.Container;
import model.UserStory;

import java.util.HashMap;

public class PrioTool {
    private HashMap<String, Command> commands = null;

    public PrioTool() {
        setupCommands();
    }
    private void setupCommands() {
        commands = new HashMap<>();

        commands.put("dump", new Dump());
        commands.put("enter", new Enter());
        commands.put("exit", new Exit());
        commands.put("help", new Help());
        commands.put("load", new Load());
        commands.put("store", new Store());
        commands.put("status",new ChangeStatus());
        commands.put("addelement", new AddElement());
        commands.put("undo", new Undo());
        commands.put("analyze", new Analyze_single());
    }
    public void start() {

        // Ausgabe eines Texts zur Begruessung
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("Prio-Tool V2.0");
        System.out.println("c/o Sascha Alda in 2018");
        System.out.println("Abgabe von kkazem2s (9031334) & mschub2s (9025083)");
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("Tip: Type 'help' for a list of all commands.");

        String strInput = null;

        // Initialisierung des Eingabe-View
        MyConsole console = new MyConsole();

        // Kontinuierliche Abfrage der Eingabe ueber While-Schleife.
        // Dann Abfrage ueber switch/case oder (hier:) if-Schleifen
        // Optimierung: Command Pattern (Kapitel 6)
        while (true) {
            try {
                //System.out.print("> ");
                strInput = console.readLine("> ");

            } catch (Exception e) {}

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");


            //for (int i = 0; i < strings.length; i++) {
            //    strings[i] = strings[i].toLowerCase();
            //}

            Command command = commands.get(strings[0]);

            //TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST
            //System.out.println("----");
            //System.out.println(Arrays.toString(strings));
            //System.out.println("----");


            try {
                if (strings[0].equals("dump") & strInput.length() > 4) {
                    try {
                        if (strings[1].equals("actor")) {
                            if (strings.length > 2) {
                                System.out.println("Für einen sortierten Dump wurde ein nicht gueltiger Befehl eingeben. Type 'help' for more infos.");
                            } else {
                                Dump d = new Dump();
                                d.execute(strings[1]);
                            }
                        } else if (strings[1].equals("status") && strings[2].equals("progress") || strings[1].equals("status") && strings[2].equals("done") || strings[1].equals("status") && strings[2].equals("todo")) {
                            if (strings.length > 3) {
                                System.out.println("Für einen sortierten Dump wurde ein nicht gueltiger Befehl eingeben. Type 'help' for more infos.");
                            } else {
                                Dump d = new Dump();
                                d.execute(strings[2]);
                            }
                        } else {
                            Dump d = new Dump();
                            d.execute(strings[1], Integer.parseInt(strings[2]));
                        }
                    } catch (Exception e) {
                        System.out.println("Für einen sortierten Dump wurde ein nicht gueltiger Befehl eingeben. Type 'help' for more infos.");
                    }
                } else if (strings[0].equals("status")) {
                    if (strings.length > 3) {
                        System.out.println("ChangeRequest konnte nicht verarbeitet werden. Type 'help' for more infos.");
                    } else {
                        try {
                            ChangeStatus cs;
                            if (strings[2].equals("todo") || strings[2].equals("progress") || strings[2].equals("done")) {
                                switch (strings[2]) {
                                    case "todo":
                                        cs = new ChangeStatus(Integer.parseInt(strings[1]), model.Status.TODO);
                                        cs.execute();
                                        break;
                                    case "progress":
                                        cs = new ChangeStatus(Integer.parseInt(strings[1]), model.Status.PROGRESS);
                                        cs.execute();
                                        break;
                                    case "done":
                                        cs = new ChangeStatus(Integer.parseInt(strings[1]), model.Status.DONE);
                                        cs.execute();
                                        break;
                                }
                                System.out.println("Die UserStory mit der ID " + strings[1] + " wurde auf den Status '" + Container.getInstance().getUserStory(Integer.parseInt(strings[1])).getStatus() + "' gesetzt");
                            } else {
                                System.out.println("ChangeRequest konnte nicht verarbeitet werden. Type 'help' for more infos.");
                            }
                        } catch (Exception e) {
                            System.out.println("ChangeRequest konnte nicht verarbeitet werden. Type 'help' for more infos.");
                        }
                    }
                } else if (strings[0].equals("addelement") || strings[0].equals("addElement")) {
                    try {
                        if (strings[1].equals("actor") || strings[1].equals("userstory")) {
                            switch(strings[1]) {
                                case "actor" :
                                    if (strings.length > 3) {
                                        System.out.println("AddElement-Request konnte nicht verarbeitet werden. Type'help'for more infos.");
                                        break;
                                    } else {
                                        AddElement ae = new AddElement(strings[2]);
                                        ae.execute();
                                        break;
                                    }
                                case "userstory":
                                    if (strings.length > 4) {
                                        System.out.println("AddElement-Request konnte nicht verarbeitet werden. Type'help'for more infos.");
                                        break;
                                    }

                                    UserStory us = Container.getInstance().getUserStory(Integer.parseInt(strings[2]));
                                    boolean isthere = false;
                                    for (Akteur a : Container.getInstance().getCurrentListAkteur()) {
                                        if (strings[3].equals(a.getName())) {
                                            isthere = true;
                                            Akteur akteur = Container.getInstance().getRealAkteur(a.getID());
                                            us.setHauptakteur(akteur);
                                            System.out.println("Der Akteur '" + akteur.getName() + "' wurde zur UserStory mit der ID '" + us.getID() + "' hinzugefügt.");
                                            break;
                                        }
                                    }
                                    if (!isthere) {
                                        System.out.println("Der Akteur '" + strings[3] + "' konnte nicht gefunden werden.");
                                        break;
                                    }
                            }
                        } else {
                            System.out.println("AddElement-Request konnte nicht verarbeitet werden. Type'help'for more infos.");
                        }
                    } catch (Exception e) {
                        System.out.println("AddElement-Request konnte nicht verarbeitet werden. Type'help'for more infos.");
                    }
                }
                else if (strings[0].equals("analyze")) {
                    int l = strings.length;
                    try {
                        if (strings[1].equals("all")) {
                            Analyze a = new Analyze_all();
                            try {
                                if (l == 2) {
                                    a.analyze();
                                    //all normal
                                } else {
                                    System.out.println("Kein gültiger 'analyze'-Befehl! Für Hilfe 'help' tippen!");
                                }
                            } catch (Exception e) {
                                System.out.println("Kein gültiger 'analyze'-Befehl! Für Hilfe 'help' tippen!");
                            }
                        } else {
                            try {
                                if (!Container.getInstance().usExist(Integer.parseInt(strings[1]))) {
                                    System.out.println("Keine UserStory mit der ID '" + strings[1] + "' zum analysieren gefunden");
                                } else if (l == 4 && strings[2].equals("details") && strings[3].equals("hints") || l == 4 && strings[2].equals("hints") && strings[3].equals("details")) {
                                    Analyze_single analyze = new Analyze_single(Container.getInstance().getUserStory(Integer.parseInt(strings[1])),true,true);
                                    analyze.execute();
                                } else if (l == 3 && strings[2].equals("hints")) {
                                    Analyze_single analyze = new Analyze_single(Container.getInstance().getUserStory(Integer.parseInt(strings[1])),false,true);
                                    analyze.execute();
                                } else if(l == 3 && strings[2].equals("details")) {
                                    Analyze_single analyze = new Analyze_single(Container.getInstance().getUserStory(Integer.parseInt(strings[1])),true,false);
                                    analyze.execute();
                                } else if (l == 2) {
                                    Analyze_single analyze = new Analyze_single(Container.getInstance().getUserStory(Integer.parseInt(strings[1])),false,false);
                                    analyze.execute();
                                }  else {
                                    System.out.println("Kein gültiger 'analyze'-Befehl! Für Hilfe 'help' tippen!");
                                }
                            } catch (Exception e) {
                                System.out.println("Kein gültiger 'analyze'-Befehl! Für Hilfe 'help' tippen!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Kein gültiger 'analyze'-Befehl! Für Hilfe 'help' tippen!");
                    }
                } else {
                    if (strings.length == 1) {
                        command.execute();
                    } else {
                        throw new Exception();
                    }
                }
            } catch (Exception e) {
                System.out.println("Command not supported! Type 'help' for a list of all commands.");
            }
        }
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */