package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {

    @Test
    void canInstantiateOneNewToyMachine() {
        assertNotEquals(null, new ToyMachine());
    }

    @Test
    void canMakeOneNewToyOnDemand() {
        ToyMachine toyMachine = new ToyMachine();
        Present present = toyMachine.givePresent();
        assertNotEquals(null, present);
    }
}