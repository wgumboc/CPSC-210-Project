package model;

import java.util.List;

// Represents an employee who has a name and a position status
public class Employee {
    private String name;
    private Boolean hasPosition;
    private SkillsList employeeSkills;

    // EFFECTS: employee has given name and defaults to has no position
    public Employee(String name) {
        this.name = name;
        hasPosition = false;
        employeeSkills = new SkillsList();
    }

    // EFFECTS: returns case number
    public String getEmployeeName() {
        return name;
    }

    // EFFECTS: returns true if employee has a position, false otherwise
    public Boolean hasPosition() {
        return hasPosition;
    }

    // MODIFIES: hasPosition
    // EFFECTS: sets hasPosition to true if employee is assigned a position
    public void giveAssignment() {
        hasPosition = true;
    }

    // MODIFIES: hasPosition
    // EFFECTS: sets hasPosition to true if employee's position is removed
    public void removeAssignment() {
        hasPosition = false;
    }

    // EFFECTS: returns the employee's list of skills
    public SkillsList getSkills() {
        return employeeSkills;
    }

}
