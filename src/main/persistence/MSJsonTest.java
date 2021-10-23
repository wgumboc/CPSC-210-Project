package persistence;

import model.Employee;
import model.SkillsList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MSJsonTest {
    protected void checkEmployee(String name, Boolean bl, SkillsList sl, Employee employee) {
        assertEquals(name, employee.getEmployeeName());
        assertEquals(bl, false);
        assertEquals(sl, )
    }
}
