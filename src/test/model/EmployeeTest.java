package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {
    private Skill skill1;
    private Skill skill2;
    private Employee employee1;


    @BeforeEach
    public void runBefore() {
        skill1 = new Skill("L1 Labeler");
        skill2 = new Skill("Filler 1");
        employee1 = new Employee("Jane Doe");
    }

    @Test
    public void testGetEmployeeName() {
        assertEquals("Jane Doe", employee1.getEmployeeName());
    }

    @Test
    public void testAssignments() {
        assertFalse(employee1.hasPosition());
        employee1.giveAssignment();
        assertTrue(employee1.hasPosition());
        employee1.removeAssignment();
        assertFalse(employee1.hasPosition());
    }

}
