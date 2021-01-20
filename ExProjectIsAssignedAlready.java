

public class ExProjectIsAssignedAlready extends Exception{

    public ExProjectIsAssignedAlready() {
        super("Project has already been assigned to a team.");
    }
    public ExProjectIsAssignedAlready(String msg) {
        super(msg);
    }
}
