package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrsClausTest {

    private static final int TEAM_SIZE = 3;
    private static final int MULTIPLE_RUNS = 10;
    static private MrsClaus mrsSanta;

    @BeforeAll
    static void setUp() {
        mrsSanta = new MrsClaus(TEAM_SIZE);
    }

    @Test
    void mrs_santa_could_be_instantiated_with_team_size() {
        assertNotEquals(null, mrsSanta);
        assertEquals(TEAM_SIZE, mrsSanta.getNumberOfTeamMembers());
    }

    @Test
    void can_run_one_time_with_multiple_elves() {
        mrsSanta.run();
    }

    @Test
    void can_run_multiple_times_with_multiple_elves() {
        assertDoesNotThrow(() -> {
            for (int n = 0; n < MULTIPLE_RUNS; n += 1) {
                mrsSanta.run();
            }
        });
    }
}