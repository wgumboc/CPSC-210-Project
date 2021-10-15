package model;

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

}
