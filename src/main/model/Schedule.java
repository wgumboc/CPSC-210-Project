package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Schedule {
    private EmployeeRoster roster;
    private PositionList positionList;
    private SkillsList qcSkillsList;

    public Schedule() {
        roster = new EmployeeRoster();
        positionList = new PositionList();
        qcSkillsList = new SkillsList();
        qcSkillsList.qcSkillsList();
    }

    // EFFECTS: returns roster
    public EmployeeRoster getRoster() {
        return roster;
    }

    // EFFECTS: returns list of positions
    public PositionList getPositionList() {
        return positionList;
    }

    // EFFECTS: returns list of qc skills
    public SkillsList getQCSkillsList() {
        return qcSkillsList;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("employees", employeesToJson());
        json.put("positions", positionsToJson());
        return json;
    }

    // EFFECTS: returns employees in this schedule as a JSON array
    private JSONArray employeesToJson() {
        JSONArray jsonArray = new JSONArray();
        List<Employee> er = roster.getRoster();

        for (Employee e : er) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns positions in this schedule as a JSON array
    private JSONArray positionsToJson() {
        JSONArray jsonArray = new JSONArray();
        List<Position> pl = positionList.getAllPositions();

        for (Position p : pl) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
