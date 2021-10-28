package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.UUID;

// Represents an employee who has a name and a position status
public class Employee implements Writable {
    private String name;
    private Boolean hasPosition;
    private SkillsList employeeSkills;
    private String employeeID;

    // REQUIRES: name is a string
    // EFFECTS: employee has given name and defaults to has no position
    public Employee(String name) {
        this.name = name;
        hasPosition = false;
        employeeSkills = new SkillsList();
        employeeID = UUID.randomUUID().toString();
    }

    // EFFECTS: returns employee name
    public String getEmployeeName() {
        return name;
    }

    // EFFECTS: returns employeeID
    public String getEmployeeID() {
        return employeeID;
    }

    // EFFECTS: sets the employee id to what user specifies
    public void setEmployeeID(String id) {
        employeeID = id;
    }

    // EFFECTS: manually sets if the employee has a position
    public void setHasPosition(Boolean b) {
        hasPosition = b;
    }

    // EFFECTS: returns true if employee has a position, false otherwise
    public Boolean hasPosition() {
        return hasPosition;
    }

    // MODIFIES: this
    // EFFECTS: sets hasPosition to true if employee is assigned a position
    public void giveAssignment() {
        hasPosition = true;
    }

    // MODIFIES: this
    // EFFECTS: sets hasPosition to true if employee's position is removed
    public void removeAssignment() {
        hasPosition = false;
    }

    // EFFECTS: returns the employee's list of skills
    public SkillsList getSkills() {
        return employeeSkills;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hasPosition", hasPosition);
        json.put("employeeSkills", skillsToJson());
        json.put("employeeID", employeeID);
        return json;
    }

    // EFFECTS: returns skills as a JSON array
    private JSONArray skillsToJson() {
        JSONArray jsonArray = new JSONArray();
        List<Skill> skillsList = employeeSkills.getList();

        for (Skill s : skillsList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
