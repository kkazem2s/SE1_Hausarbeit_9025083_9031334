package commands;

import model.Container;
import model.Status;

public class ChangeStatus implements Command {
    private int id;
    private Status st;

    public ChangeStatus() {}
    public ChangeStatus(int id, Status st) {
        this.id = id;
        this.st = st;
    }
    @Override
    public void execute() {
        Container.getInstance().getUserStory(this.id).setStatus(st);
    }
}
/**
 * @author kkazem2s	- 9031334
 * 		   mschub2s - 9025083
 */
