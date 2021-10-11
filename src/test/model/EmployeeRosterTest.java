package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRosterTest {
    private EmployeeRoster roster;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void runBefore() {
        roster = new EmployeeRoster();
        employee1 = new Employee("Jane Doe");
        employee2 = new Employee("John Smith");
    }

    @Test
    public void testAddRemoveEmployee() {
        assertEquals(0, roster.rosterSize());

        roster.addEmployee(employee1);
        roster.addEmployee(employee2);

        assertEquals(2, roster.rosterSize());
        assertEquals(employee1, roster.getEmployee(0));
        assertEquals(employee2, roster.getEmployee(1));

        roster.removeEmployee(employee1);

        assertEquals(1, roster.rosterSize());
        assertEquals(employee2, roster.getEmployee(0));

        roster.removeEmployee(employee1);

        assertEquals(1, roster.rosterSize());

        roster.removeEmployee(employee2);

        assertEquals(0, roster.rosterSize());
    }

}