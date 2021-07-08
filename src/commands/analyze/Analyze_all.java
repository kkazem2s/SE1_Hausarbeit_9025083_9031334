package commands.analyze;

import commands.Analyze;
import model.Container;
import model.UserStory;

import java.util.List;

public class Analyze_all implements Analyze {
    private int rating;
    private List<UserStory> list;

    public Analyze_all(){
        this.list = Container.getInstance().getCurrentList();
    }
    @Override
    public void execute() {
        analyze();
    }
    @Override
    public void analyze(){
        if (list.size() == 0) {
            System.out.println("Keine UserStories zum analysieren vorhanden!");
        } else {
            for (UserStory us : list) {
                Analyze_single as = new Analyze_single(us,false,false);
                as.analyze();
            }
            getAvg();
            System.out.println("Ihre " + list.size() + " UserStories haben durchschnittlich " +
                    "folgende Qualität: ");
            if(rating == 100){
                System.out.println(rating + "% (sehr gut)");
            }else if(rating >= 80 && rating < 100){
                System.out.println(rating + "% (gut)");
            }else if(rating >= 60 && rating < 80){
                System.out.println(rating + "% (befriedigend)");
            }else if(rating >= 40 && rating < 60){
                System.out.println(rating + "% (ausreichend)");
            }else {
                System.out.println(rating + "% (mangelhaft)");
            }
        }
    }
    private void getAvg() {
        for(UserStory us : list) {
            rating += us.getRating();
        }
        rating = rating / list.size();
    }
}
