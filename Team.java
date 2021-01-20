
import java.util.*;

public class Team implements Comparable<Team> {
    private String teamName;
    private Employee head;
    private Day dateSetup; // the date this team was setup
    private ArrayList<Employee>TeamMembers;
    private ArrayList<Project>HandlingProjects;
    public Team(String Tn, Employee e) {
        this.HandlingProjects=new ArrayList<>();
        this.TeamMembers=new ArrayList<>();
        this.TeamMembers.add(e);
        this.teamName = Tn;
        this.head=e;
        e.joinTeam(this);
        dateSetup = SystemDate.getInstance().clone();// Set teamName, head, setup date
    }
    //get
    private Employee gethead() {
        return head;
    }
    public String getTeamName() {
		return this.teamName;
    }
    public int getNumbersOfTeamMembers() {
        return TeamMembers.size();
	}
    //search 
    public static Team searchTeam(ArrayList<Team> list, String nameToSearch) {
        for (Team t : list) {
            if (t.getTeamName().equals(nameToSearch))
                return t;// search the arrayList for the employee with the given name
        }
        return null;
    }
    //list
	public static void list(ArrayList<Team> list) {
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t : list){
            System.out.printf("%-15s%-10s%-14s",t.getTeamName(), t.gethead().getName(), t.dateSetup.toString());
            t.listMembers();
        }
    }
    private void listMembers() {
        if (this.TeamMembers.isEmpty()||this.TeamMembers.size()==1){
            System.out.println("(no member)");
        }
        else{
            for (Employee e : TeamMembers){
                if(e.getName()!=head.getName())
                    System.out.print(e.getName()+" ");
            }
            System.out.println();
        }
    }
    // EmployeeJoin/Leave
    public void EmployeeJoinTeam(Employee e) {
    TeamMembers.add(e);
    Collections.sort(TeamMembers);
    }
	public void EmployeeLeaveTeam(Employee e) {
    TeamMembers.remove(e);
    Collections.sort(TeamMembers);
    }
    //Project-related
    public void registProject(Project p) {
    this.HandlingProjects.add(p);
    }
    public void unregistProject(Project p) {
    this.HandlingProjects.remove(p);
    }
    public boolean checkTeamAvailabe(Project p) {
		for(Project pj:HandlingProjects){
            if(p.getEndDay().getComparableInt()>=pj.getStartDay().getComparableInt()
            &&p.getEndDay().getComparableInt()<=pj.getEndDay().getComparableInt())
                return false;
            if(p.getStartDay().getComparableInt()>=pj.getStartDay().getComparableInt()
            &&p.getStartDay().getComparableInt()<=pj.getEndDay().getComparableInt())
                return false;
            if(p.getStartDay().getComparableInt()<=pj.getStartDay().getComparableInt()&&
            p.getEndDay().getComparableInt()>=pj.getEndDay().getComparableInt())
                return false;
        }
        return true;
	}
    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }

}





