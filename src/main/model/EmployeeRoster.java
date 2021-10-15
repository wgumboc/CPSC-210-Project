package model;

import java.util.ArrayList;
import java.util.List;

// Represents the employee roster of the department with maximum size MAX_SIZE
public class EmployeeRoster {
    private List<Employee> roster;
    public static final int MAX_SIZE = 100;

    //EFFECTS: Creates an empty list for the roster
    public EmployeeRoster() {
        roster = new ArrayList<>();
    }

    //EFFECTS: returns the number of employees in the roster
    public int rosterSize() {
        return roster.size();
    }

    // REQUIRES: 0 <= i <= size of roster
    // EFFECTS: returns an employee in the roster
    public Employee getEmployee(int i) {
        return roster.get(i);
    }

    // MODIFIES: roster
    // EFFECTS: adds an employee to the roster
    public void addEmployee(Employee employee) {
        roster.add(employee);
    }

    // MODIFIES: roster
    // EFFECTS: removes an employee from the roster
    public void removeEmployee(Employee employee) {
        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i) == employee) {
                roster.remove(i);
                break;
            }
        }
    }

}
