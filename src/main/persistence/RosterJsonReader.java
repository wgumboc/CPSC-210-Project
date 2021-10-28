package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Employee;
import model.EmployeeRoster;
import org.json.*;

// All code below templated from JsonSerializationDemo
public class RosterJsonReader {
    private String source;

    // ***Templated from JsonSerializationDemo***
    // EFFECTS: constructs reader to read from source file
    public RosterJsonReader(String source) {
        this.source = source;
    }

    // ***Templated from JsonSerializationDemo***
    // EFFECTS: reads EmployeeRoster from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EmployeeRoster read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEmployeeRoster(jsonObject);
    }

    // ***Copied from JsonSerializationDemo***
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // ***Templated from JsonSerializationDemo***
    // EFFECTS: parses (convert string to Json object) EmployeeRoster from JSON object and returns it
    private EmployeeRoster parseEmployeeRoster(JSONObject jsonObject) {
        EmployeeRoster er = new EmployeeRoster();
        addEmployees(er, jsonObject);
        return er;
    }

    // MODIFIES: er
    // EFFECTS: parses employees from JSON object and adds them to workroom
    private void addEmployees(EmployeeRoster er, JSONObject jsonObject) {
        JSONArray employees = jsonObject.getJSONArray("employees");
        for (Object json : employees) {
            JSONObject nextEmployee = (JSONObject) json;
            addEmployee(er, nextEmployee);
        }
    }

    // ***Templated from JsonSerializationDemo***
    // MODIFIES: er
    // EFFECTS: parses Employee from JSON object and adds it to workroom
    private void addEmployee(EmployeeRoster er, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Employee employee = new Employee(name);

        employee.setHasPosition(jsonObject.getBoolean("hasPosition"));

        employee.setEmployeeID(jsonObject.getString("employeeID"));

        er.addEmployee(employee);
    }
}
