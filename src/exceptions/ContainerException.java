package exceptions;

public class ContainerException extends Exception {
	public ContainerException() {
		super("Das UserStory-Objekt wurde nicht gefunden!");
	}
	public ContainerException(int id) {
		super("Die UserStory mit der ID '" +id+ "' ist bereits vorhanden oder existiert nicht!");
	}
	public ContainerException(String msg) {
		super(msg);
	}
}
/**
 * @author kkazem2s	- 9031334 
 * 		   mschub2s - 9025083
 */