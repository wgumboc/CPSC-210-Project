package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// ***Methods templated from JsonSerializationDemo***
public class MSJsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public MSJsonReader(String source) {
        this.source = source;
    }

    // ***copied from JsonSerializationDemo***
    // EFFECTS: reads schedule from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    // ***copied from JsonSerializationDemo***
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses schedule from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        Schedule schedule = new Schedule();

        EmployeeRoster er = schedule.getRoster();
        PositionList pl = schedule.getPositionList();
        SkillsList sl = schedule.getQCSkillsList();

        addEmployees(er, sl, jsonObject);
        addPositions(pl, sl, er, jsonObject);

        return schedule;
    }

    // MODIFIES: schedule
    // EFFECTS: parses EmployeeRoster from JSON object and adds to Schedule
    private void addEmployees(EmployeeRoster er, SkillsList sl, JSONObject jsonObject) {
        JSONArray employees = jsonObject.getJSONArray("employees");
        for (Object json : employees) {
            JSONObject nextEmployee = (JSONObject) json;
            addEmployee(er, sl, nextEmployee);
        }
    }

    // MODIFIES: er
    // EFFECTS: parses Employee from JSON object and adds to EmployeeRoster
    private void addEmployee(EmployeeRoster er, SkillsList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Employee employee = new Employee(name);

        employee.setHasPosition(jsonObject.getBoolean("hasPosition"));

        employee.setEmployeeID(jsonObject.getString("employeeID"));

        JSONArray skillsList = jsonObject.getJSONArray("employeeSkills");
        for (Object json : skillsList) {
            JSONObject nextSkill = (JSONObject) json;
            addSkills(employee, sl, nextSkill);
        }

        er.addEmployee(employee);
    }

    // MODIFIES: employee
    // EFFECTS: parses Skill from JSON object and adds to Employee
    private void addSkills(Employee employee, SkillsList sl, JSONObject jsonObject) {
        String skillName = jsonObject.getString("skillName");
        List<Skill> skills = sl.getList();
        SkillsList employeeSkills = employee.getSkills();

        for (Skill s: skills) {
            if (skillName.equals(s.getSkillName())) {
                employeeSkills.addSkill(s);
            }
        }
    }

    // MODIFIES: schedule
    // EFFECTS: parses PositionList from JSON object and adds to Schedule
    private void addPositions(PositionList pl, SkillsList sl, EmployeeRoster er, JSONObject jsonObject) {
        JSONArray positions = jsonObject.getJSONArray("positions");
        for (Object json : positions) {
            JSONObject nextPosition = (JSONObject) json;
            addPosition(pl, sl, er, nextPosition);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses Position from JSON object and adds to PositionList
    private void addPosition(PositionList pl, SkillsList sl, EmployeeRoster er, JSONObject jsonObject) {
        Skill posSkill = null;
        List<Skill> skills = sl.getList();

        String name = jsonObject.getString("name");
        String skill = jsonObject.getString("requiredSkill");

        for (Skill s: skills) {
            if (skill.equals(s.getSkillName())) {
                posSkill = s;
            }
        }
        
        Position position = new Position(name, posSkill);

        position.setPositionFull(jsonObject.getBoolean("positionFull"));

        position.setPositionEmployee(er, jsonObject.getString("positionEmployee"));

        pl.addPosition(position);
    }
}
