package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static de.pruefbit.kata.WorkPlan.DEFAULT_PRODUCTION_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

class MrsClausTest {
    private static final int TEAM_SIZE = 3;
    private static final int PRODUCTION_LIMIT = 5;
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
        mrsClaus.run();
        assertEquals(DEFAULT_PRODUCTION_LIMIT, mrsClaus.getCargoList().size());
    }

    @Test
    void can_be_instantiated_with_no_family_work_plan() {
        WorkPlan wp = new WorkPlan();
        wp.setProductionLimit(PRODUCTION_LIMIT);
        wp.setTeamSize(TEAM_SIZE);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.run();
        assertEquals(PRODUCTION_LIMIT, mrsClaus.getCargoList().size());
    }

    @Test
    void can_be_instantiated_with_family_work_plan() {
        WorkPlan wp = new WorkPlan();
        wp.setFamilies(FAMILIES);
        wp.setProductionLimit(PRODUCTION_LIMIT);
        wp.setTeamSize(TEAM_SIZE);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.run();
        assertEquals(FAMILIES.size() * PRODUCTION_LIMIT, mrsClaus.getCargoList().size());
    }

    @Test
    void mrsClaus_drops_packets_for_naughty_families() {
        WorkPlan wp = new WorkPlan();
        wp.setFamilies(FAMILIES);
        wp.setProductionLimit(PRODUCTION_LIMIT);
        wp.setTeamSize(TEAM_SIZE);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.dropPresentsForNaughtyFamily("Kowalsky");
        mrsClaus.dropPresentsForNaughtyFamily("Kojak");
        mrsClaus.run();
        assertEquals((FAMILIES.size() - 2) * PRODUCTION_LIMIT, mrsClaus.getCargoList().size());
    }
}