package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionListTest {
    private PositionList allPositions;
    private Position position1;
    private Position position2;
    private Skill skill1;
    private Skill skill2;


    @BeforeEach
    public void runBefore() {
        allPositions = new PositionList();
        skill1 = new Skill("L1 Lab");
        skill2 = new Skill("CFB");
        position1 = new Position("L1 Labeler", skill1);
        position2 = new Position("CF Batcher", skill2);
    }

    @Test
    public void testAddRemovePosition() {
        assertEquals(0, allPositions.positionListSize());

        allPositions.addPosition(position1);
        allPositions.addPosition(position2);

        assertEquals(2, allPositions.positionListSize());
        assertEquals(position1, allPositions.getPosition(0));
        assertEquals(position2, allPositions.getPosition(1));

        allPositions.removePosition(position1);

        assertEquals(1, allPositions.positionListSize());
        assertEquals(position2, allPositions.getPosition(0));

        allPositions.removePosition(position1);

        assertEquals(1, allPositions.positionListSize());

        allPositions.removePosition(position2);

        assertEquals(0, allPositions.positionListSize());
    }

}