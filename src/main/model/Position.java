package model;

// Represents a position with a position name and a employee that fills it
public class Position {
    private String name;
    private Employee positionEmployee;
    private Skill requiredSkill;

    //EFFECTS: Position has a given name and defaults to no employee filled
    public Position(String name, Skill requiredSkill) {
        this.name = name;
        positionEmployee = null;
    }

    //EFFECTS: Returns position name.
    public String getPositionName() {
        return name;
    }

    //EFFECTS: Fills a position with an employee if the employee does not have a position.
    public Boolean fillPosition(Employee employee) {
        if (positionEmployee == null) {
            if (!employee.hasPosition()) {
                this.positionEmployee = employee;
                employee.giveAssignment();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //EFFECTS: Removes the employee assigned to the position.
    public Boolean removeEmployee() {
        if (positionEmployee == null) {
            return false;
        } else {
            positionEmployee.removeAssignment();
            positionEmployee = null;
            return true;
        }
    }

    //EFFECTS: returns the employee who has filled the position
    public Employee getPositionEmployee() {
        return positionEmployee;
    }

}
