import java.util.ArrayList;

public class Project implements Comparable<Project> {

    private String pcode;
    private int mdays;
    private Team HandlingTeam;
    private Day StartDay;
    private Day EndDay;

    public Project(String pcode, int mdays) {
        this.pcode = pcode;
        this.mdays = mdays;
        this.HandlingTeam = null;
        this.StartDay=null;
        this.EndDay=null;
    }

    public static void list(ArrayList<Project> allProjects) {
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");
        for (Project pj : allProjects) {
            if(pj.getHandlingTeam()==null)
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", pj.pcode, pj.mdays + " man-days", "(Not Assigned)", "", "");
            else{
                Team t=pj.getHandlingTeam();
                Day S=pj.StartDay;
                Day E=pj.EndDay;    
                System.out.printf("%-9s%-14s%-13s%-13s%-13s\n",pj.pcode,pj.mdays+" man-days",t.getTeamName(),S.toString(),E.toString());
            }
            }
    }

    // get
    private String getPcode() {
        return this.pcode;
    }

    public Team getHandlingTeam() {
        return this.HandlingTeam;
    }
    public int getDayNeed(){
        if (this.mdays%(HandlingTeam.getNumbersOfTeamMembers())==0)
            return mdays/HandlingTeam.getNumbersOfTeamMembers();
        else{
            return mdays/HandlingTeam.getNumbersOfTeamMembers()+1;
        }
    }
    public Day getStartDay() {
		return this.StartDay;
	}

	public Day getEndDay() {
		return this.EndDay;
	}
    // search
    public static Project searchProject(ArrayList<Project> allProjects, String pcodeToSearch) {
        for (Project pj : allProjects) {
            if (pj.getPcode().equals(pcodeToSearch))
                return pj;// search the arrayList for the employee with the given name
        }
        return null;
    }

    // Team Operation
    public void registTeam(Team t) {
        this.HandlingTeam = t;
    }

    public void unregistTeam() {
        this.HandlingTeam=null;
    }

    // set
    public void setStartAndEndDay(Day Sd) {
        this.StartDay = Sd;
        this.EndDay=Sd.clone();
        int DayNeed=this.getDayNeed();
        for(int i=0;i<DayNeed-1;i++)
            this.EndDay=EndDay.next();
    }

    public void unsetStartAndEndDay() {
        this.StartDay = null;
        this.EndDay=null;
    }

    @Override
    public int compareTo(Project another) {
        return this.pcode.compareTo(another.pcode);
    }



}
