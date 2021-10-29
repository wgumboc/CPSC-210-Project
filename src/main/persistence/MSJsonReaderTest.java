package persistence;

import model.Employee;
import model.EmployeeRoster;
import model.Schedule;
import model.SkillsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Templated from JsonSerializationDemo
public class MSJsonReaderTest {
    protected void checkEmployee(String name, Boolean bl, Employee employee) {
        assertEquals(name, employee.getEmployeeName());
        assertEquals(bl, employee.hasPosition());
    }

    @Test
    void testReaderNonExistentFile() {
        MSJsonReader reader = new MSJsonReader("./data/invalid.json");
        try {
            Schedule sch = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Templated from JsonSerializationDemo
    @Test
    void testReadSchedule() {
        MSJsonReader reader = new MSJsonReader("./data/testEmployeeRoster.json");
        try {
            Schedule sch = reader.read();
            assertEquals(5, er.rosterSize());
            checkEmployee("Jayden", false, er.getEmployee(0));
            checkEmployee("Kayden", false, er.getEmployee(1));
            checkEmployee("Hayden", false, er.getEmployee(2));
            checkEmployee("Aiden", false, er.getEmployee(3));
            checkEmployee("Shaeden", false, er.getEmployee(4));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
