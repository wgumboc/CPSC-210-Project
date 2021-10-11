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

    @Test
    public void testAddRemoveSkill() {
        List<Skill> skills = employee1.getSkills();
        assertEquals(0, skills.size());

        employee1.addSkill(skill1);
        employee1.addSkill(skill2);

        assertEquals(2, skills.size());
        assertEquals(skill1, skills.get(0));
        assertEquals(skill2, skills.get(1));

        employee1.removeSkill(skill1);

        assertEquals(1, skills.size());
        assertEquals(skill2, skills.get(0));

        employee1.removeSkill(skill1);

        assertEquals(1, skills.size());

        employee1.removeSkill(skill2);

        assertEquals(0, skills.size());
    }

}
