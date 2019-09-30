package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {

    private static final int NUM_PRESENTS_PER_FAMILY = 10;

    @Test
    void can_instantiate_one_new_toy_machine() {
        assertNotEquals(null, new ToyMachine());
    }

    @Test
    void can_instantiate_with_num_presents_per_family() {
        assertNotEquals(null, new ToyMachine(NUM_PRESENTS_PER_FAMILY));
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
    void new_machines_may_work_with_family_feature() {
        ToyMachine toyMachine = new ToyMachine(NUM_PRESENTS_PER_FAMILY);
        Present present = toyMachine.givePresent();
        assertNotEquals(null, present.getFamily());
    }
}