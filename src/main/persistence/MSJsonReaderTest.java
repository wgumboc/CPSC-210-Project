package persistence;

import model.Employee;
import model.EmployeeRoster;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MSJsonReaderTest {
    @Test
    void testReaderGeneralWorkRoom() {
        MSJsonReader reader = new MSJsonReader("./data/testEmployeeRoster.json");
        try {
            EmployeeRoster er = reader.read();
            assertEquals(5, er.rosterSize());
            checkThingy("needle", Category.STITCHING, thingies.get(0));
            checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
