public class CmdChangeTeam extends RecordedCommand{
    private Employee e;
    private Team oTeam;
    private Team nTeam;
    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3)
                throw new ExInsufficientCArguments();
            Company c=Company.getInstance();
            e=c.getEmployee(cmdParts[1]);
            if(e==null)
                throw new ExEmployeeNameNotExist(); 
            nTeam=c.getTeam(cmdParts[2]);
            if(nTeam==null)
                throw new ExTeamNotExist();
            oTeam=e.getbelongTeam();
            if(nTeam.equals(oTeam))
                throw new ExOldAndNewTeamTheSame();
            c.changeTeam(oTeam,e,nTeam);
            addUndoCommand(this); 
            clearRedoList(); 
            System.out.println("Done.");
        }catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        }catch (ExEmployeeNameNotExist e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotExist e) {
            System.out.println(e.getMessage());
        } catch (ExOldAndNewTeamTheSame e) {
            System.out.println(e.getMessage());
        }
    
    }

    @Override
    public void undoMe(){
        Company c=Company.getInstance();
        c.changeBackTeam(nTeam,e,oTeam);
        addRedoCommand(this);
    }
    @Override
    public void redoMe(){
        Company c=Company.getInstance();
        c.changeTeam(oTeam,e,nTeam);
        addUndoCommand(this);
    }

}
