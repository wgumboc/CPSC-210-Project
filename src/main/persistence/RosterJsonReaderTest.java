package persistence;

import model.Employee;
import model.EmployeeRoster;
import model.SkillsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RosterJsonReaderTest {
    protected void checkEmployee(String name, Boolean bl, Employee employee) {
        SkillsList skillsList;
        assertEquals(name, employee.getEmployeeName());
        assertEquals(bl, employee.hasPosition());

    }

    // Templated from JsonSerializationDemo
//    @Test
//    void testReaderGeneralEmployeeList() {
//        MSJsonReader reader = new MSJsonReader("./data/testEmployeeRoster.json");
//        try {
//            EmployeeRoster er = reader.read();
//            assertEquals(5, er.rosterSize());
//            checkEmployee("Jayden", false, er.getEmployee(0));
//            checkEmployee("Kayden", false, er.getEmployee(1));
//            checkEmployee("Hayden", false, er.getEmployee(2));
//            checkEmployee("Aiden", false, er.getEmployee(3));
//            checkEmployee("Shaeden", false, er.getEmployee(4));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}
