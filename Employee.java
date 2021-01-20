import java.util.*;
public class Employee implements Comparable<Employee> {
    private String name;
    private Team belongTeam;

    public Employee(String n) {
        this.name = n;
        this.belongTeam=null;
    }
    //join/quit Team
	public void joinTeam(Team t) {
        this.belongTeam=t;
    }
    public void quitTeam(Team t) {
        this.belongTeam=null;
    }

    //get method
    public String getName() {
        return name;
    }
    public Team getbelongTeam() {
		return this.belongTeam;
	}
    //search 
    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        for (Employee e : list) {
            if (e.getName().equals(nameToSearch))
                return e;// search the arrayList for the employee with the given name
        }
        return null;
    }

    @Override
    public int compareTo(Employee another) {
        return this.name.compareTo(another.name);
    }




}
