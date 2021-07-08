package commands;

import model.Container;

public class Load implements Command {
    @Override
    public void execute() {
        Container container = Container.getInstance();
        container.load();
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */
