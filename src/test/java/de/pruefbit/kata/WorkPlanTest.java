package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkPlanTest {
    private static final int TEAM_SIZE = 3;

    @Test
    void team_size_can_be_set() {
        WorkPlan wp = new WorkPlan();
        wp.setTeamSize(TEAM_SIZE);
        assertEquals(TEAM_SIZE, wp.getTeamSize());
    }
}