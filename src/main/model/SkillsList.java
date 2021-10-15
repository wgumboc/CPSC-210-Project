package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillsList {
    private List<Skill> skillsList;
    private String skillName;

    public SkillsList() {
        skillsList = new ArrayList<>();
    }

    // EFFECTS: Creates a list of skills with preset skills for the Quality Department
    public void qcSkillsList() {
        Skill cfCsdBatch = new Skill("Cold Fill CSD");
        Skill cfRSBatch = new Skill("Cold Fill Rockstar");
        Skill hfGatBatch = new Skill("Line 5 Gatorade");
        Skill hfDoleBatch = new Skill("Line 5 Dole");
        Skill hfSobeBatch = new Skill("Line 5 Sobe");
        Skill cip = new Skill("CIP");
        Skill sanGeneral = new Skill("Sanitation - General");
        Skill sanExternal = new Skill("Sanitation - Externals");
        Skill micro = new Skill("Microbiology Tech");
        Skill leadHand = new Skill("Lead Hand");

        List<Skill> temp = Arrays.asList(cfCsdBatch, cfRSBatch, hfGatBatch, hfDoleBatch, hfSobeBatch,
                cip, sanGeneral, sanExternal, micro, leadHand);

        skillsList.addAll(temp);
    }

    public void addSkill(Skill skill) {
        skillsList.add(skill);
    }

    public int skillsListSize() {
        return skillsList.size();
    }

    public Skill getSkill(int i) {
        return skillsList.get(i);
    }

    //MODIFIES: roster
    //EFFECTS: removes an employee from the roster
    public void removeSkill(Skill skill) {
        for (int i = 0; i < skillsList.size(); i++) {
            if (skillsList.get(i) == skill) {
                skillsList.remove(i);
                break;
            }
        }
    }

}
