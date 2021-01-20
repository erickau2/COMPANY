public class ExEmployeeJoinedAnotherTeam extends Exception{
 

    public ExEmployeeJoinedAnotherTeam() {
        super("Employee has joined a team already.");
    }
    public ExEmployeeJoinedAnotherTeam(String msg){
        super(msg);
    }

}
