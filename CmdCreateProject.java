
public class CmdCreateProject extends RecordedCommand {
    private Project pj;

    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3)
                throw new ExInsufficientCArguments();
            int manpower = Integer.parseInt(cmdParts[2]);
            if (manpower < 1)
                throw new ExWrongEstManpower(manpower);
            Company c = Company.getInstance();
            pj = c.createProject(cmdParts[1], manpower);
            addUndoCommand(this); // <====== store this command (addUndoCommand is implemented in
                                  // RecordedCommand.java)
            clearRedoList(); // <====== There maybe some commands stored in the redo list. Clear them.
            System.out.println("Done.");
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format -- Please enter an integer.");
        } catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        } catch (ExWrongEstManpower e) {
            e.printWmanpowerMsg();
        } catch(ExProjectCodeAlreadyExists e){
            System.out.println(e.getMessage());
        }
    
    }

    @Override
    public void undoMe(){
        Company c=Company.getInstance();
        c.removeProject(pj);
        addRedoCommand(this);
    }
    @Override
    public void redoMe(){
        Company c=Company.getInstance();
        c.addProject(pj);
        addUndoCommand(this);
    }

}
