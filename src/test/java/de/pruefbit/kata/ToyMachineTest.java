package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {

    @Test
    void can_instantiate_one_new_toy_machine() {
        assertNotEquals(null, new ToyMachine());
    }

    @Test
    void machine_can_make_one_new_toy_on_demand() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNotEquals(null, present);
    }
}