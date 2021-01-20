

public class ExProjectNotExist extends Exception {
    public ExProjectNotExist() {
        super("Project does not exist.");
    }
    public ExProjectNotExist(String msg) {
        super(msg);
    }
}
