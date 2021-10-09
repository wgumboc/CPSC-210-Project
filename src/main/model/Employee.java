package model;

import java.util.List;

// Represents an employee who has a name and a shift preference
public class Employee {
    private String name;
    private String shiftPreference;
    private List<Skill> skillList;

    // EFFECTS: employee has given name and defaults to no shift preference
    public Employee(String name) {
        this.name = name;
        shiftPreference = "none";
    }

    // EFFECTS: returns case number
    public String getName() {
        return name;
    }

    // EFFECTS: returns case number
    public String getShiftPreference() {
        return shiftPreference;
    }

    // MODIFIES: this
    // EFFECTS: sets shift preference to AM
    public void setPreferenceAM() {
        shiftPreference = "AM";
    }

    // MODIFIES: this
    // EFFECTS: sets shift preference to PM
    public void setPreferencePM() {
        shiftPreference = "PM";
    }

    // MODIFIES: this
    // EFFECTS: sets shift preference to GY
    public void setPreferenceGY() {
        shiftPreference = "GY";
    }

}
