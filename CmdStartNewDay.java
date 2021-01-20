
public class CmdStartNewDay  extends RecordedCommand{
    private String pre;
    private String pro;
    @Override
    public void execute (String[] cmdParts) {
        SystemDate SD=SystemDate.getInstance();
        pre=SD.toString();
        pro=cmdParts[1];
        SD.set(pro);

        addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		System.out.println("Done.");
    }
    @Override
    public void undoMe(){
        SystemDate SD=SystemDate.getInstance();
        SD.set(pre);
        addRedoCommand(this);
    }
    @Override
    public void redoMe(){
        SystemDate SD=SystemDate.getInstance();
        SD.set(pro);
        addUndoCommand(this);
    }

}
