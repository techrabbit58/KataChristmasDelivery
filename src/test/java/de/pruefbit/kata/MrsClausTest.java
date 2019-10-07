package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrsClausTest {

    private static final int TEAM_SIZE = 3;
    private static final int MULTIPLE_RUNS = 3;
//    private static final int MAX_PRESENTS_PER_FAMILY = 10;
//    private static final List<String> FAMILIES = Arrays.asList(
//            "Kowalsky",
//            "McFly",
//            "Miller",
//            "O'Hara",
//            "Kojak"
//    );

    static private MrsClaus mrsClaus;

    @Test
    void throws_error_if_instantiated_with_zero() {
        assertThrows(IllegalArgumentException.class, () -> new MrsClaus(0));
    }

    @Test
    void throws_error_if_instantiated_with_negative() {
        assertThrows(IllegalArgumentException.class, () -> new MrsClaus(-3));
    }

    @Test
    void can_run_one_time_with_multiple_elves() {
        mrsClaus = new MrsClaus(TEAM_SIZE);
        assertDoesNotThrow(() -> mrsClaus.run());
    }

    @Test
    void can_run_multiple_times_with_multiple_elves() {
        mrsClaus = new MrsClaus(TEAM_SIZE);
        assertDoesNotThrow(() -> {
            for (int n = 0; n < MULTIPLE_RUNS; n += 1) {
                mrsClaus.run();
            }
        });
    }

    @Test
    void can_create_mrs_claus_by_builder() {
        mrsClaus = new MrsClaus.Builder().build();
        assertDoesNotThrow(() -> mrsClaus.run());
    }

    @Test
    void builder_based_mrs_claus_runs_with_a_team() {
        mrsClaus = new MrsClaus.Builder().setTeamSize(TEAM_SIZE).build();
        assertDoesNotThrow(() -> mrsClaus.run());
    }

    @Test
    void builder_based_mrs_claus_runs_with_the_millers() {
        mrsClaus = new MrsClaus.Builder()
                .addFamily("Miller")
                .build();
        assertDoesNotThrow(() -> mrsClaus.run());
    }
}