package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MrsClausTest {

    private static final int TEAM_SIZE = 3;
    private static final int PRODUCTION_LIMIT = 3;
    private static final List<String> FAMILIES = Arrays.asList(
            "Kowalsky",
            "McFly",
            "Miller",
            "O'Hara",
            "Kojak"
    );

    @Test
    void can_be_instantiated_with_defaults() {
        MrsClaus mrsClaus = new MrsClaus();
        assertNotNull(mrsClaus);
    }

    @Test
    void can_be_instantiated_with_no_family_work_plan() {
        WorkPlan wp = new WorkPlan();
        wp.setProductionLimit(PRODUCTION_LIMIT);
        wp.setTeamSize(TEAM_SIZE);
        MrsClaus mrsClaus = new MrsClaus(wp);
        assertNotNull(mrsClaus);
    }
}