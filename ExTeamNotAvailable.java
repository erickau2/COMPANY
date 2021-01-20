public class ExTeamNotAvailable extends Exception{

    public ExTeamNotAvailable() {
        super("The team is not available during the period ");
    }
    public ExTeamNotAvailable(String msg) {
        super(msg);
    }
} 
