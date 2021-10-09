package model;

// Represents a position with a position name and a employee that fills it
public class Position {
    private String name;
    private Employee positionEmployee;

    //EFFECTS: Position has a given name and defaults to no employee filled
    public Position(String name) {
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
            if (employee.hasPosition() == false) {
                this.positionEmployee = employee;
                employee.giveAssignment();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //EFFECTS:
    public Employee getPositionEmployee() {
        return positionEmployee;
    }

}
