package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MrsClausTest {

    private static final int TEAM_SIZE = 3;
    private static final int MULTIPLE_RUNS = 3;
    private static final int MAX_PRESENTS_PER_FAMILY = 10;
    private static final List<String> FAMILIES = Arrays.asList(
            "Kowalsky",
            "McFly",
            "Miller",
            "O'Hara",
            "Kojak"
    );

    static private MrsClaus mrsClaus;

    @Test
    void throws_error_if_instantiated_with_zero() {
        assertThrows(IllegalArgumentException.class, () -> new MrsClaus.Builder().setNumberOfTeamMembers(0).build());
    }

    @Test
    void throws_error_if_instantiated_with_negative() {
        assertThrows(IllegalArgumentException.class, () -> new MrsClaus.Builder().setNumberOfTeamMembers(-3).build());
    }

    @Test
    void can_run_one_time_with_multiple_elves() {
        mrsClaus = new MrsClaus.Builder().setNumberOfTeamMembers(TEAM_SIZE).build();
        assertDoesNotThrow(() -> mrsClaus.run());
    }

    @Test
    void can_run_multiple_times_with_multiple_elves() {
        mrsClaus = new MrsClaus.Builder().setNumberOfTeamMembers(TEAM_SIZE).build();
        assertDoesNotThrow(() -> {
            for (int n = 0; n < MULTIPLE_RUNS; n += 1) {
                mrsClaus.run();
            }
        });
    }

    @Test
    void can_run_for_the_families() {
        mrsClaus = new MrsClaus.Builder()
                .setNumberOfTeamMembers(TEAM_SIZE)
                .setMaxPresentsPerFamily(MAX_PRESENTS_PER_FAMILY)
                .addFamilies(FAMILIES)
                .build();
        mrsClaus.run();
    }
}