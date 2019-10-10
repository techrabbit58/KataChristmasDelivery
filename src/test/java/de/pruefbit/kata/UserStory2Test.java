package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStory2Test {

    @Test
    void mrsClaus_can_control_multiple_toyMachine_and_Elf() {
        final int TEAM_SIZE = 493;
        final int PRODUCTION_LIMIT = 1031;
        WorkPlan wp = new WorkPlan();
        wp.setTeamSize(TEAM_SIZE);
        wp.setProductionLimit(PRODUCTION_LIMIT);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.run();
        assertEquals(PRODUCTION_LIMIT, mrsClaus.getCargoList().size());
    }
}
