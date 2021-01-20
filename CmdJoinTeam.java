
public class CmdJoinTeam extends RecordedCommand {
    private Team t;
    private Employee e;

    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3) 
                throw new ExInsufficientCArguments();
            Company c = Company.getInstance();
            e = c.getEmployee(cmdParts[1]);
            if (e == null)
                throw new ExEmployeeNameNotExist();
            if(e.getbelongTeam()!=null)
                throw new ExEmployeeJoinedAnotherTeam();
            t = c.getTeam(cmdParts[2]);
            if(t==null)
                throw new ExTeamNotExist();
            c.addEmployeeToTeam(e,t);
            addUndoCommand(this); 
            clearRedoList(); 
            System.out.println("Done.");
        }catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeNameNotExist e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeJoinedAnotherTeam e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotExist e) {
            System.out.println(e.getMessage());
        }
            
        }
        @Override
        public void undoMe() {
            Company c = Company.getInstance();
            c.removeEmployeeFromTeam(e,t);
            addRedoCommand(this);
        }
    
        @Override
        public void redoMe() {
            Company c = Company.getInstance();
            c.addEmployeeToTeam(e,t);
            addUndoCommand(this);
        }
        
}
