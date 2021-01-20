
public class CmdTakeProject extends RecordedCommand {

    private Team t;
    private Project p;
    private Day d,sD,eD;
    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 4)
                throw new ExInsufficientCArguments();
            Day SD=SystemDate.getInstance();
            Company c = Company.getInstance();
            t=c.getTeam(cmdParts[1]);
            if(t==null)
                throw new ExTeamNotExist();
            p=c.getProject(cmdParts[2]);
            if(p==null)
                throw new ExProjectNotExist();
            if(p.getHandlingTeam()!=null)
                throw new ExProjectIsAssignedAlready();
            String[] DayParts=cmdParts[3].split("-");
            if(DayParts.length!=3)
                throw new ExInvalidDate();            
            d=new Day(cmdParts[3]);
            if(!Day.valid(d.getYear(),d.getMonth(),d.getDay()))
                throw new ExInvalidDate();
            if(d.getComparableInt()<=SD.getComparableInt())
                throw new ExEarlierStartDay();     
            p.registTeam(t);
            p.setStartAndEndDay(d);
            if(!t.checkTeamAvailabe(p)){ 
                sD=p.getStartDay().clone();
                eD=p.getEndDay().clone(); 
                p.unregistTeam();
                p.unsetStartAndEndDay();
                throw new ExTeamNotAvailable();      
            }
            t.registProject(p);
            addUndoCommand(this);
		    clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientCArguments e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotExist e) {
            System.out.println(e.getMessage());
        }catch (ExProjectNotExist e) {
            System.out.println(e.getMessage());
        } catch (ExProjectIsAssignedAlready e) {
            System.out.println(e.getMessage());
        } catch (ExEarlierStartDay e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotAvailable e) {
            System.out.print(e.getMessage());
            System.out.printf("(%s to %s).",sD.toString(),eD.toString());
        } catch (ExInvalidDate e) {
            System.out.print(e.getMessage());
        }

    }

    @Override
    public void undoMe() {
        t.unregistProject(p);
        p.unregistTeam();
        p.unsetStartAndEndDay();
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        t.registProject(p);
        p.registTeam(t);
        p.setStartAndEndDay(d);
        addUndoCommand(this);
    }

}
