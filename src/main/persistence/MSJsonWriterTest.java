package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Templated from jsonSerializationDemo
public class MSJsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MSJsonWriter writer = new MSJsonWriter("./data/invalid.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmployees() {
        try {
            Schedule schedule = new Schedule();
            EmployeeRoster er = schedule.getRoster();

            er.addEmployee(new Employee("Jason"));
            er.addEmployee(new Employee("Jackson"));

            MSJsonWriter writer = new MSJsonWriter("./data/testWriterSchedule.json");
            writer.open();
            writer.write(schedule);
            writer.close();

            MSJsonReader reader = new MSJsonReader("./data/testWriterSchedule.json");
            schedule = reader.read();
            er = schedule.getRoster();

            List<Employee> roster = er.getRoster();
            assertEquals("Jason", er.getEmployee(0).getEmployeeName());
            assertEquals("Jackson", er.getEmployee(1).getEmployeeName());
            assertEquals(2, roster.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWritePositions() {
        try {
            Schedule schedule = new Schedule();
            PositionList pl = schedule.getPositionList();

            pl.addPosition(new Position("AM Batcher", new Skill("Rockstar")));
            pl.addPosition(new Position("AM Line 5", new Skill("Gatorade")));

            Position p = pl.getPosition(0);
            p.setPositionEmployee(new Employee("Jack"));

            MSJsonWriter writer = new MSJsonWriter("./data/testWriterSchedule.json");
            writer.open();
            writer.write(schedule);
            writer.close();

            MSJsonReader reader = new MSJsonReader("./data/testWriterSchedule.json");
            schedule = reader.read();
            pl = schedule.getPositionList();

            List<Position> positionList = pl.getAllPositions();
            assertEquals("AM Batcher", pl.getPosition(0).getPositionName());
            assertEquals("Jack", p.getPositionEmployee().getEmployeeName());
            assertEquals("AM Line 5", pl.getPosition(1).getPositionName());
            assertEquals(2, positionList.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
