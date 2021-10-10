package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

// Represents an employee who has a name and a position status
public class Employee {
    private String name;
    private Boolean hasPosition;
    private List<Skill> skillsList;

    // EFFECTS: employee has given name and defaults to has no position
    public Employee(String name) {
        this.name = name;
        hasPosition = false;
        skillsList = new ArrayList<>();
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
    public List<Skill> getSkills() {
        return skillsList;
    }

    // MODIFIES: skillsList
    // EFFECTS: adds a skill to the employee's list of skills
    public void addSkill(Skill skill) {
        skillsList.add(skill);
    }

    // MODIFIES: skillsList
    // EFFECTS: removes a skill from the employee's list of skills
    public void removeSkill(Skill skill) {
        for (int i = 0; i < skillsList.size(); i++) {
            if (skillsList.get(i) == skill) {
                skillsList.remove(i);
                break;
            }
        }
    }

}
