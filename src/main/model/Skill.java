package model;

import org.json.JSONObject;

public class Skill {
    private String skillName;

    // EFFECTS: creates a skill with a given name
    public Skill(String skillName) {
        this.skillName = skillName;
    }

    // EFFECTS: returns the name of the skill
    public String getSkillName() {
        return skillName;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("skillName", skillName);
        return json;
    }

}
