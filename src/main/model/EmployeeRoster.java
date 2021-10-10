package model;

import java.util.ArrayList;
import java.util.List;

// Represents the employee roster of the department with maximum size MAX_SIZE
public class EmployeeRoster {
    private List<Employee> roster;
    public static final int MAX_SIZE = 100;

    public EmployeeRoster() {
        roster = new ArrayList<>();
    }

    public int rosterSize() {
        return roster.size();
    }

    public Employee getEmployee(int i) {
        return roster.get(i);
    }

    public void addEmployee(Employee employee) {
        roster.add(employee);
    }

    public void removeEmployee(Employee employee) {
        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i) == employee) {
                roster.remove(i);
                break;
            }
        }
    }

}
