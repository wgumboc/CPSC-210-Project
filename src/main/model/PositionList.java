package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PositionList {
    // Represents the all current positions within the department
    private List<Position> allPositions;

    //EFFECTS: Creates an empty list for the roster
    public PositionList() {
        allPositions = new ArrayList<>();
    }

    //EFFECTS: returns the list of positions
    public List<Position> getAllPositions() {
        return allPositions;
    }

    //EFFECTS: returns the number of positions in the list
    public int positionListSize() {
        return allPositions.size();
    }

    //EFFECTS: returns a position in the list
    public Position getPosition(int i) {
        return allPositions.get(i);
    }

    //MODIFIES: this
    //EFFECTS: adds a position to the list
    public void addPosition(Position position) {
        allPositions.add(position);
    }

    //MODIFIES: this
    //EFFECTS: removes a position from the list
    public void removePosition(Position position) {
        for (int i = 0; i < allPositions.size(); i++) {
            if (allPositions.get(i) == position) {
                allPositions.remove(i);
                break;
            }
        }
    }
}
