

public class ExTeamNotExist extends Exception {
    public ExTeamNotExist() {
        super("Team does not exist.");
    }
    public ExTeamNotExist(String msg) {
        super(msg);
    }
}
