package model;

import java.util.ArrayList;
import java.util.List;

public class SkillsList {
    private List<Skill> skillsList;
    private String skillName;

    public SkillsList() {
        skillsList = new ArrayList<>();
    }

    public List<Skill> getSkillsList() {
        return skillsList;
    }

}
