package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserStory3Test {
    private static final int TEAM_SIZE = 5;
    private static final int PRODUCTION_LIMIT = 3;
    private static final List<String> FAMILIES = Arrays.asList(
            "Murtaugh",
            "McFly",
            "Miller",
            "O'Hara",
            "Kojak",
            "Simpson",
            "Bellamy",
            "Walton",
            "Cartwright"
    );

    @Test
    void loading_the_sleigh_obeys_family_cohesion() {
        WorkPlan wp = new WorkPlan();
        wp.setTeamSize(TEAM_SIZE);
        wp.setFamilies(FAMILIES);
        wp.setProductionLimit(PRODUCTION_LIMIT);
        MrsClaus mrsClaus = new MrsClaus(wp);
        mrsClaus.run();
        for (String item: mrsClaus.getCargoList()) {
            System.out.println(item);
        }
        assertEquals(PRODUCTION_LIMIT * FAMILIES.size(), mrsClaus.getCargoList().size());
    }
}
