package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {

    static private Elf elf;
    static private boolean elfHasCalledBack;

    static void callback() {
        elfHasCalledBack = true;
    }

    @BeforeAll
    static void setup() {
        ToyMachine toyMachine = new ToyMachine();
        SantasSleigh santasSleigh = new SantasSleigh();
        elf = new Elf(toyMachine::givePresent, santasSleigh::pack, ElfTest::callback);
    }

    @Test
    void couldInstantiateOneElfWithDependencies() {
        assertNotEquals(null, elf);
    }

    @Test
    void elfPicksAndDeliversOnePacketToSantaSleigh() {
        elfHasCalledBack = false;
        elf.run();
        assertTrue(elfHasCalledBack);
    }

    @Test
    void elfCanPickAndDeliverMultiplePacketsInSequence() {
        long MULTIPLE_PACKETS = 100;
        for (long n = 1; n <= MULTIPLE_PACKETS; n += 1) {
            elfHasCalledBack = false;
            elf.run();
            assertTrue(elfHasCalledBack);
        }
    }
}