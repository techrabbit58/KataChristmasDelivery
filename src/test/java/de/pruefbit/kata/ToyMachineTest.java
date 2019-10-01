package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {
    private static final int MAX_PRESENTS_PER_FAMILY = 3;

    @Test
    void can_instantiate_one_new_toy_machine() {
        assertNotEquals(null, new ToyMachine());
    }

    @Test
    void can_instantiate_with_num_presents_per_family() {
        assertNotEquals(null, new ToyMachine.Builder()
                .setMaxPresentsPerFamily(MAX_PRESENTS_PER_FAMILY)
                .build());
    }

    @Test
    void machine_can_make_one_new_toy_on_demand() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNotEquals(null, present);
    }

    @Test
    void new_machines_may_work_for_unspecified_family() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNull(present.getFamily());
    }

    @Test
    void new_machines_can_work_for_the_millers() {
        ToyMachine toyMachine = new ToyMachine.Builder()
                .addFamily("Miller")
                .setMaxPresentsPerFamily(1)
                .build();
        Present present = toyMachine.givePresent();
        assertEquals("Miller", present.getFamily());
    }

    @Test
    void new_machines_can_work_for_multiple_families() {
        List<String> families = Arrays.asList("Kowalski", "O'Hara", "McFly");
        ToyMachine.Builder tmBuilder = new ToyMachine.Builder();
        families.forEach(tmBuilder::addFamily);
        ToyMachine toyMachine = tmBuilder
                .setMaxPresentsPerFamily(1)
                .build();
        for (int n = 0; n < families.size(); n += 1) {
            Present present = toyMachine.givePresent();
            assertTrue(families.contains(present.getFamily()));
        }
    }

    @Test
    void toy_machines_with_families_need_positive_max_presents() {
        assertThrows(RuntimeException.class, () -> new ToyMachine.Builder().addFamily("Miller").build());
    }

    @Test
    void toy_machine_build_without_families_is_ok() {
        assertDoesNotThrow(() -> {
            new ToyMachine.Builder().build();
            new ToyMachine.Builder().setMaxPresentsPerFamily(0).build();
            new ToyMachine.Builder().setMaxPresentsPerFamily(MAX_PRESENTS_PER_FAMILY).build();
        });
    }

    @Test
    void provides_null_with_family_and_presents_limit_exceeded() {
        String family = "Miller";
        ToyMachine tm = new ToyMachine.Builder().addFamily(family).setMaxPresentsPerFamily(1).build();
        Present p1 = tm.givePresent();
        assertEquals(family, p1.getFamily());
        Present p2 = tm.givePresent();
        assertNull(p2);
    }
}