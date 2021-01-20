public class CmdHire extends RecordedCommand {
    private Employee e;

    @Override
    public void execute(String[] cmdParts) {
        try {
            if (cmdParts.length < 2) 
                throw new ExInsufficientCArguments();
            Company c = Company.getInstance();
            e = c.createEmployee(cmdParts[1]);
            addUndoCommand(this); 
            clearRedoList(); 
            System.out.println("Done.");
        } catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeNameAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe(){
        Company c= Company.getInstance();
        c.removeEmployee(e);
        addRedoCommand(this);
    }
    @Override
    public void redoMe(){
        Company c= Company.getInstance();
        c.addEmployee(e);
        addUndoCommand(this);
    }
}
