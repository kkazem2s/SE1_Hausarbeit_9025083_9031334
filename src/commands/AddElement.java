package commands;

import model.Container;

public class AddElement implements Command {
    private String name;

    public AddElement() {}
    public AddElement(String name) {
        this.name = name;
    }
    @Override
    public void execute() {
        Container.getInstance().addAkteur(this.name);
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */