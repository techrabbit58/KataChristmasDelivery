package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserStory4Test {

    @Test
    void mrsSanta_drops_presents_for_naughty_families() {
        final String NAUGHTY_FAMILY = "McNaughty";
        WorkPlan wp = new WorkPlan();
        wp.setTeamSize(1);
        wp.setFamilies(Collections.singletonList(NAUGHTY_FAMILY));
        wp.setProductionLimit(1);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.dropPresentsForNaughtyFamily(NAUGHTY_FAMILY);
        mrsClaus.run();
        assertEquals(0, mrsClaus.getCargoList().size());
    }
}
