package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SkillTest {
    private Skill skill1;

    @Test
    public void testGetSkillName() {
        skill1 = new Skill("Line 1 Labeler");
        assertEquals("Line 1 Labeler", skill1.getSkillName());
    }
}
