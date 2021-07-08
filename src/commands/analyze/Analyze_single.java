package commands.analyze;
import commands.Analyze;
import model.UserStory;

import static commands.analyze.Values.*;

public class Analyze_single implements Analyze {
    private boolean details;
    private boolean hints;
    private int rating;
    private UserStory us;

    public Analyze_single() {}
    public Analyze_single(UserStory us, boolean details, boolean hints) {
        this.us = us;
        this.details = details;
        this.hints = hints;
    }
    @Override
    public void execute() {
        System.out.println("Die UserStory mit der ID: " + us.getID() + " hat folgenden Qualität: ");
        analyze();
        this.rating = us.getRating();
        if(rating == 100){
            System.out.println(us.getRating() + "% (sehr gut)");
        }else if(rating >= 80 && rating < 100){
            System.out.println(us.getRating() + "% (gut)");
        }else if(rating >= 60 && rating < 80){
            System.out.println(us.getRating() + "% (befriedigend)");
        }else if(rating >= 40 && rating < 60){
            System.out.println(us.getRating() + "% (ausreichend)");
        }else {
            System.out.println(us.getRating() + "% (mangelhaft)");
        }
        if (details) {
            details();
        }
        if (hints) {
            hints();
        }
    }
    @Override
    public void analyze() {
        us.setRating(100 - checkAkteur() - checkMehrwert() - checkRisiko());
    }
    private void details() {
        int sum = 0;
        System.out.println("\nDetails:");
        if (checkAkteur() == strafe_akteur) {
            System.out.println("Kein Akteur vorhanden! (-" + strafe_akteur+"%)");
            sum+=strafe_akteur;
        }
        if (checkMehrwert() == strafe_mehrwert) {
            System.out.println("Kein schriftlicher Mehrwert vorhanden! (-" + strafe_mehrwert + "%)");
            sum+=strafe_mehrwert;
        }
        if (checkRisiko() == strafe_risiko) {
            System.out.println("Kein schriftliches Risiko vorhanden! (-" +strafe_risiko + "%)");
            sum+=strafe_risiko;
        }
        if (sum == 0) {
            System.out.println("Alles ok");
        }
    }
    private void hints() {
        int sum = 0;
        System.out.println("\nHints:");
        if (checkAkteur() == strafe_akteur) {
            System.out.println("Weisen sie der UserStory einen (Haupt-)Akteur zu!");
            sum+=strafe_akteur;
        }
        if (checkMehrwert() == strafe_mehrwert) {
            System.out.println("Fügen sie einen ausreichenden schriftlichen Mehrwert hinzu!");
            sum+=strafe_mehrwert;
        }
        if (checkRisiko() == strafe_risiko) {
            System.out.println("Fügen sie ein ausreichendes schriftliches Risiko hinzu!");
            sum+=strafe_risiko;
        }
        if (sum == 0) {
            System.out.println("Alles ok");
        }
    }
    public int checkMehrwert(){
        if(us.getS_mehrwert().length() <= minimum || us.getS_mehrwert().length() >= maximum){
            return strafe_mehrwert;
        } else{
            return 0;
        }
    }
    public int checkRisiko(){
        if(us.getS_risiko().length() <= minimum || us.getS_risiko().length() >= maximum){
            return strafe_risiko;
        } else{
            return 0;
        }
    }
    public int checkAkteur() {
        if (!us.has_hauptakteur_bol()) {
            return strafe_akteur;
        } else {
            return 0;
        }
    }
}
