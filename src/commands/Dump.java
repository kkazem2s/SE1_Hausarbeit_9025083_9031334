package commands;
import model.Akteur;
import model.Container;
import model.Status;
import model.UserStory;
import java.util.List;


public class Dump implements Command {
    List<UserStory> list = Container.getInstance().getCurrentList();
    List<Akteur> akteure = Container.getInstance().getCurrentListAkteur();

    @Override
    public void execute() {
        System.out.println("UserStories:\n");
        Container.getInstance().dump();
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("Akteure:\n");
        Container.getInstance().dump_actor();
    }
    public void execute(String string, int n) {
        if (Container.getInstance().getCurrentList().size() == 0) {
            System.out.println("Keine UserStories vorhanden!");
        } else {
            string.toLowerCase();
            switch(string) {
                case "aufwand":
                    if (!Container.getInstance().exists("aufwand",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getAufwand() >= n)                      // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "id":
                    if (Container.getInstance().exists("id",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getID() >= n)                           // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "mehrwert":
                    if (!Container.getInstance().exists("mehrwert",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getMehrwert() >= n)                     // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "risiko":
                    if (!Container.getInstance().exists("risiko",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getRisiko() >= n)                       // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "strafe":
                    if (!Container.getInstance().exists("strafe",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getStrafe() >= n)                       // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "prio":
                    if (!Container.getInstance().exists("prio",n)) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getPrio() >= n)                         // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                default:
                    System.out.println("Für einen sortierten Dump wurde ein falscher Befehl eingeben. Type 'help' for more infos.");
                    break;
            }
        }
    }
    public void execute(String string) {
        string.toLowerCase();
        if (string.equals("todo") || string.equals("progress") || string.equals("done") || string.equals("actor")) {
            switch (string) {
                case "todo":
                    if (Container.getInstance().getCurrentList().size() == 0) {
                        System.out.println("Keine UserStories vorhanden!");
                    } else if (!Container.getInstance().exist("todo")) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getStatus() == Status.TODO)            // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "progress":
                    if (Container.getInstance().getCurrentList().size() == 0) {
                        System.out.println("Keine UserStories vorhanden!");
                    } else if (!Container.getInstance().exist("progress")) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getStatus() == Status.PROGRESS)        // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "done":
                    if (Container.getInstance().getCurrentList().size() == 0) {
                        System.out.println("Keine UserStories vorhanden!");
                    } else if (!Container.getInstance().exist("done")) {
                        System.out.println("Keine UserStories nach Suchkriterien vorhanden!");
                    } else {
                        list.stream()
                                .filter(element -> element.getStatus() == Status.DONE)            // Filter
                                .sorted((i1, i2) ->  Double.compare( i1.getPrio(), i2.getPrio())) // Map (Sortierung)
                                .forEach(element -> System.out.println(element));                 // Reduce (forEach)
                    }
                    break;
                case "actor":
                    if (Container.getInstance().getAnzahl_Akteure() == 0) {
                        System.out.println("Keine Akteure vorhanden!");
                    } else {
                        for (Akteur akteur : akteure) {
                            System.out.println(akteur.toString());
                        }
                    }
                    break;
            }
        } else {
            System.out.println("Für einen sortierten Dump wurde ein falscher Befehl eingeben. Type 'help' for more infos.");
        }
    }
}
/**
 * @author kkazem2s	- 9031334 
 * 		   mschub2s - 9025083
 */
