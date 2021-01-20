public class CmdSetupTeam extends RecordedCommand {
    private Team t;
    private Employee e;
    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3)
                throw new ExInsufficientCArguments();
            Company c = Company.getInstance();
            
            t = c.createTeam(cmdParts[1], cmdParts[2]);
            e=c.getEmployee(cmdParts[2]);
            addUndoCommand(this);
            clearRedoList(); 
            System.out.println("Done.");
        } catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        }  catch (ExTeamNameAlreadyExists e) {
            System.out.println(e.getMessage());
        } catch(ExEmployeeJoinedAnotherTeam e){
            System.out.println(e.getMessage());
        }catch (ExEmployeeNameNotExist e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe(){
        Company c=Company.getInstance();
        c.removeTeam(t);
        e.quitTeam(t);
        addRedoCommand(this);
    }
    @Override
    public void redoMe(){
        Company c=Company.getInstance();
        c.addTeam(t);
        e.joinTeam(t);
        addUndoCommand(this);
    }
}
