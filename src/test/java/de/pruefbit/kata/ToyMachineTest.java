package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {
    private static final int MAX_PRESENTS_PER_FAMILY = 3;

    @Test
    void can_instantiate_with_num_presents_per_family() {
        assertNotNull(new ToyMachine.Builder()
                .setMaxPresentsPerFamily("Miller", MAX_PRESENTS_PER_FAMILY)
                .build());
    }

    @Test
    void machine_can_make_one_new_toy_on_demand() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNotNull(present);
    }

    @Test
    void new_machines_works_for_unspecified_family() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNotNull(present.getFamily());
    }

    @Test
    void new_machines_can_work_for_the_millers() {
        ToyMachine toyMachine = new ToyMachine.Builder()
                .addFamily("Miller")
                .setMaxPresentsPerFamily("Miller", 1)
                .build();
        Present present = toyMachine.givePresent();
        assertEquals("Miller", present.getFamily());
    }

    @Test
    void new_machines_can_work_for_multiple_families() {
        List<String> families = Arrays.asList("Kowalski", "O'Hara", "McFly");
        ToyMachine.Builder tmBuilder = new ToyMachine.Builder();
        families.forEach(tmBuilder::addFamily);
        families.forEach(f -> tmBuilder.setMaxPresentsPerFamily(f, 1));
        ToyMachine toyMachine = tmBuilder.build();
        for (int n = 0; n < families.size(); n += 1) {
            Present present = toyMachine.givePresent();
            assertTrue(families.contains(present.getFamily()));
        }
    }

    @Test
    void toy_machine_build_without_families_is_ok() {
        assertDoesNotThrow(() -> {
            new ToyMachine.Builder().build();
        });
    }

    @Test
    void negative_not_allowed_for_max_presents() {
        assertThrows(RuntimeException.class, () -> new ToyMachine.Builder().setMaxPresentsPerFamily("Miller", -1));
    }

    @Test
    void provides_null_with_family_and_presents_limit_exceeded() {
        String family = "Miller";
        ToyMachine tm = new ToyMachine.Builder().addFamily(family).setMaxPresentsPerFamily("Miller", 1).build();
        Present p1 = tm.givePresent();
        assertEquals(family, p1.getFamily());
        Present p2 = tm.givePresent();
        assertNull(p2);
    }
}