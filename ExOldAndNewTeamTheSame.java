

public class ExOldAndNewTeamTheSame extends Exception{

    public ExOldAndNewTeamTheSame() {
        super("The old and new teams should not be the same.");
    }
    public ExOldAndNewTeamTheSame(String msg) {
        super(msg);
    }
}
