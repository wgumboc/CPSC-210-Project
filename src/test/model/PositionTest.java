package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Skill skill1;
    private Skill skill2;
    private Position position1;
    private Position position2;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void runBefore() {
        skill1 = new Skill("L1 Labeler");
        skill2 = new Skill("Filler 1");
        position1 = new Position("Line 1 Labeler", skill1);
        position2 = new Position("Filler 1", skill2);
        employee1 = new Employee("Jane Doe");
        employee2 = new Employee("John Smith");
    }

    @Test
    public void testGetPositionName() {
        assertEquals("Line 1 Labeler", position1.getPositionName());
    }

    @Test
    public void testGetPositionSkill() {
        assertEquals(skill1, position1.getPositionSkill());
    }

    @Test
    public void testFillPosition() {
        assertTrue(position1.fillPosition(employee1)); //position is open and employee has no position
        assertEquals(employee1, position1.getPositionEmployee()); //ensure correct employee is assigned to position
        assertFalse(position1.fillPosition(employee2)); //ensure you cannot fill a position that is filled
        assertFalse(position2.fillPosition(employee1)); //ensure you cannot assign an employee who is already assigned
    }

    @Test
    public void testRemovePosition() {
        position1.fillPosition(employee1);

        assertEquals(employee1, position1.getPositionEmployee());
        assertTrue(position1.removeEmployee());
        assertNull(position1.getPositionEmployee());
        assertFalse(position1.removeEmployee());
    }

    @Test
    public void testIsFull() {
        assertFalse(position1.isFull());

        position1.fillPosition(employee1);

        assertTrue(position1.isFull());
    }

    @Test
    public void testAssignSkill() {
        position1.assignSkill(skill1);
        assertEquals(skill1, position1.getPositionSkill());
    }

}