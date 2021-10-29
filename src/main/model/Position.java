package model;

import org.json.JSONObject;

import java.util.List;

// Represents a position with a position name and a employee that fills it
public class Position {
    private String name;
    private Employee positionEmployee;
    private Skill requiredSkill;
    private Boolean positionFull;

    // EFFECTS: Position has a given name and defaults to no employee filled
    public Position(String name, Skill requiredSkill) {
        this.name = name;
        this.requiredSkill = requiredSkill;
        positionEmployee = null;
        positionFull = false;
    }

    // EFFECTS: Returns position name.
    public String getPositionName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Fills a position with an employee if the employee does not have a position.
    public Boolean fillPosition(Employee employee) {
        if (positionEmployee == null) {
            if (!employee.hasPosition()) {
                this.positionEmployee = employee;
                employee.giveAssignment();
                positionFull = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Sets positionFull to the boolean passed
    public void setPositionFull(Boolean b) {
        positionFull = b;
    }

    // MODIFIES: this
    // EFFECTS: Sets positionEmployee to the employee whose ID matches the ID passed
    public void setPositionEmployee(EmployeeRoster er, String s) {
        String id;
        List<Employee> roster = er.getRoster();

        for (Employee e: roster) {
            id = e.getEmployeeID();
            if (id.equals(s)) {
                positionEmployee = e;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets positionEmployee to a given employee
    public void setPositionEmployee(Employee employee) {
        positionEmployee = employee;
    }

    // MODIFIES: this
    // EFFECTS: Removes the employee assigned to the position.
    public Boolean removeEmployee() {
        if (positionEmployee == null) {
            return false;
        } else {
            positionEmployee.removeAssignment();
            positionEmployee = null;
            positionFull = false;
            return true;
        }
    }

    //EFFECTS: returns the employee who has filled the position
    public Employee getPositionEmployee() {
        return positionEmployee;
    }

    //EFFECTS: returns the skill required for the position
    public Skill getPositionSkill() {
        return requiredSkill;
    }

    //EFFECTS: returns true if position is filled already, false otherwise
    public Boolean isFull() {
        return positionFull;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        String id;

        if (positionEmployee == null) {
            id = "null";
        } else {
            id = positionEmployee.getEmployeeID();
        }

        json.put("name", name);
        json.put("positionEmployee", id);
        json.put("requiredSkill", requiredSkill.getSkillName());
        json.put("positionFull", positionFull);
        return json;
    }

}
