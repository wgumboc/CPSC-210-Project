package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the employee roster of the department with maximum size MAX_SIZE
public class EmployeeRoster implements Writable {
    private List<Employee> roster;

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

    // MODIFIES: this
    // EFFECTS: adds an employee to the roster
    public void addEmployee(Employee employee) {
        roster.add(employee);
    }

    // MODIFIES: this
    // EFFECTS: removes an employee from the roster
    public void removeEmployee(Employee employee) {
        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i) == employee) {
                roster.remove(i);
                break;
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("employees", employeesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray employeesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Employee e : roster) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
