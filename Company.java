import java.util.ArrayList;
import java.util.Collections;
public class Company {
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    private static Company instance = new Company();

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }
    //get
    public Employee getEmployee(String name) {
		return Employee.searchEmployee(allEmployees, name);
	}
    
	public Team getTeam(String tname) {
		return Team.searchTeam(allTeams, tname);
	}

    public Project getProject(String pcode) {
		return Project.searchProject(allProjects,pcode);
	}
    //list
    public void listTeams() { // See how it is called in main()
        Team.list(allTeams); // allTeams
    }

    public void listEmployees() {
        for (Employee e : allEmployees) {
            
            Team t=e.getbelongTeam();
            if(t!=null)
                System.out.println(e.getName()+" ("+t.getTeamName()+")");
            else    
                System.out.println(e.getName());
        }
    }

	public void listProjects() {
        Project.list(allProjects);
	}
    //create
    public Employee createEmployee(String n) throws ExEmployeeNameAlreadyExists { // See how it is called in main()
        Employee e=Employee.searchEmployee(allEmployees, n);
        if(e!=null)
            throw new ExEmployeeNameAlreadyExists();
        e=new Employee(n);
        allEmployees.add(e);
        Collections.sort(allEmployees); // allEmployees
        return e; // the return value is useful for later undoable command.
    }

    public Team createTeam(String Tn, String En) throws ExEmployeeNameNotExist, ExTeamNameAlreadyExists,
            ExEmployeeJoinedAnotherTeam 
        {
        Team t=Team.searchTeam(allTeams, Tn);
        if(t!=null)
            throw new ExTeamNameAlreadyExists();                                                                                                  // in main()
        Employee e = Employee.searchEmployee(allEmployees,En);
        if(e==null)
            throw new ExEmployeeNameNotExist();
        if(e.getbelongTeam()!=null)
            throw new ExEmployeeJoinedAnotherTeam();
        t = new Team(Tn, e);
        e.joinTeam(t);
        allTeams.add(t);
        Collections.sort(allTeams); // allTeams
        return t; // the return value is useful for later undoable command.
    }
    public Project createProject(String Pcode, int Mdays) throws ExProjectCodeAlreadyExists {
        
        Project pj=Project.searchProject(allProjects,Pcode);
        if(pj!=null)
            throw new ExProjectCodeAlreadyExists();
        pj= new Project(Pcode, Mdays);
        allProjects.add(pj);
        Collections.sort(allProjects);
        return pj;
    }
    //add/remmove
    public void addEmployee(Employee e) {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee e) {
        allEmployees.remove(e); // .remove is a method of ArrayList
    }

    public void addTeam(Team t) {
        allTeams.add(t);
        Collections.sort(allTeams);
    }

    public void removeTeam(Team t) {
        allTeams.remove(t);
        Collections.sort(allTeams);
    }

    public void addProject(Project pj) {
        allProjects.add(pj);
        Collections.sort(allProjects);
    }

    public void removeProject(Project pj) {
        allProjects.remove(pj);
        Collections.sort(allProjects);
    }
    //Team Operation
	public void addEmployeeToTeam(Employee e, Team t) {
        e.joinTeam(t);
        t.EmployeeJoinTeam(e);
    }

	public void removeEmployeeFromTeam(Employee e, Team t) {
        e.quitTeam(t);
        t.EmployeeLeaveTeam(e);
    }

	public void changeTeam(Team oTeam, Employee e, Team nTeam) {
        removeEmployeeFromTeam(e,oTeam);
        addEmployeeToTeam(e,nTeam);      
    }

	public void changeBackTeam(Team nTeam, Employee e, Team oTeam) {
        removeEmployeeFromTeam(e,nTeam);
        addEmployeeToTeam(e,oTeam);
    }
}
