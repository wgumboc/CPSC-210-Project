package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

// Represents an employee who has a name and a position status
public class Employee {
    private String name;
    private Boolean hasPosition;

    // EFFECTS: employee has given name and defaults to has no position
    public Employee(String name) {
        this.name = name;
        hasPosition = false;
    }

    // EFFECTS: returns case number
    public String getName() {
        return name;
    }

    public Boolean hasPosition() {
        return hasPosition;
    }

    // MODIFIES: hasPosition
    // EFFECTS: sets hasPosition to true if employee is assigned a position
    public void giveAssignment() {
        hasPosition = true;
    }

    public void removeAssignment() {
        hasPosition = false;
    }

}
