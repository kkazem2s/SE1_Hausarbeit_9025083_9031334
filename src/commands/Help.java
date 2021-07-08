package commands;

public class Help implements Command {
    @Override
    public void execute() {
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("Folgende Commands stehen in dieser Version des Tools zur Verfügung:");
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("addelement actor [Name] : Fügt einen neuen Akteur mit [Name] hinzu.");
        System.out.println("addelement userstory [id] [Name des Akteur] : Fügt vorhandenen Akteur [Name des Akteur] zu einer UserStory [id] hinzu.");
        System.out.println("---");
        System.out.println("analyze [all | id] : Die Qualität einer UserStory [id], oder von allen [all] kann geprüft werden.");
        System.out.println("analyze [id] details : Es werden Details zur Qualität einer UserStory [id] ausgegeben.");
        System.out.println("analyze [id] hints : Es werden Hinweise zur Qualitätsverbesserung einer UserStory [id] ausgegeben.");
        System.out.println("analyze [id] details hints : Es werden Hinweise zur Qualität und Details zur Qualitätsverbesserung der UserStory [id] ausgegeben.");
        System.out.println("---");
        System.out.println("enter: Eingabe einer neuen UserStory");
        System.out.println("---");
        System.out.println("dump: Zeigt alle aktuellen UserStories und Akteure an");
        System.out.println("dump [Sortierparameter] [Mindestanzahl]: Zeigt UserStories mit Mindestwert des Parameters an.");
        System.out.println("                                         Als 'Sortierparameter' gilt jede Eigenschaft einer UserStory.");
        System.out.println("                                                --> [Aufwand | ID | Mehrwert | Risiko | Strafe | Prio]");
        System.out.println("dump status [StatusWert]: Zeigt alle aktuellen UserStories mit dem StatusWert an");
        System.out.println("                --> [TODO | PROGRESS | DONE]");
        System.out.println("dump actor : Zeigt alle registrierten Akteure an");
        System.out.println("---");
        System.out.println("exit: Beendet die Software.");
        System.out.println("---");
        System.out.println("load: Laden von zuvor gespeicherten UserStories und Akteuren.");
        System.out.println("---");
        System.out.println("store: Speichert eine Kollektion von UserStories und Akteuren als Datei unter /Users/Public/Documents/.");
        System.out.println("---");
        System.out.println("status [id] [NeuerWert]: Veraendert den Status einer UserStory");
        System.out.println("                --> [TODO | PROGRESS | DONE]");
        System.out.println("---");
        System.out.println("undo: Entfernt die zuletzt erstellte UserStory bzw. Akteur.");
        System.out.println("_______________________________________________________________________________________________________________________________");
        System.out.println("Prio-Tool V2.0");
        System.out.println("c/o Sascha Alda in 2018");
        System.out.println("Abgabe von kkazem2s (9031334) & mschub2s (9025083)");
        System.out.println("_______________________________________________________________________________________________________________________________");
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */
