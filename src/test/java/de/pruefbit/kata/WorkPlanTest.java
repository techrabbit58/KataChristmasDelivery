package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkPlanTest {
    private static final int TEAM_SIZE = 3;
    private static final int PRODUCTION_LIMIT = 3;

    private WorkPlan workPlan;

    @BeforeEach
    void setupEach() {
        workPlan = new WorkPlan();
    }

    @Test
    void team_size_can_be_set() {
        workPlan.setTeamSize(TEAM_SIZE);
        assertEquals(TEAM_SIZE, workPlan.getTeamSize());
    }

    @Test
    void production_limit_can_be_set() {
        workPlan.setProductionLimit(PRODUCTION_LIMIT);
        assertEquals(PRODUCTION_LIMIT, workPlan.getProductionLimit());
    }
}