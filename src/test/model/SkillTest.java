package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillTest {
    private Skill skill;

    @Test
    public void testGetSkillName() {
        skill = new Skill("Line 1 Labeler");
        assertEquals("Line 1 Labeler", skill.getSkillName());
    }
}
