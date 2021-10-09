package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    private Position position1;
    private Position position2;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void runBefore() {
        position1 = new Position("Line 1 Labeler");
        position2 = new Position("Filler 1");
        employee1 = new Employee("Jane Doe");
        employee2 = new Employee("John Smith");
    }

    @Test
    public void testGetPositionName() {
        assertEquals("Line 1 Labeler", position1.getPositionName());
    }

    @Test
    public void testFillPosition() {
        assertTrue(position1.fillPosition(employee1)); //position is open and employee has no position
        assertEquals(employee1, position1.getPositionEmployee()); //ensure correct employee is assigned to position
        assertFalse(position1.fillPosition(employee2)); //ensure you cannot fill a position that is filled
        assertFalse(position2.fillPosition(employee1)); //ensure you cannot assign an employee who is already assigned
    }

}