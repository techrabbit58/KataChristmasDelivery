package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {

    static private ToyMachine toyMachine;
    static private SantaSleigh santaSleigh;
    static private Elf elf;

    @BeforeAll
    static void setup() {
        toyMachine = new ToyMachine();
        santaSleigh = SantaSleigh.getSleigh();
        elf = new Elf(toyMachine::givePresent, santaSleigh::pack);
    }

    @Test
    void canInstantiateOneElfWithDependencies() {
        assertNotEquals(null, new Elf(toyMachine::givePresent, santaSleigh::pack));
    }

    @Test
    void elfPicksAndDeliversOnePacketToSantaSleigh() {
        long packetsOnSleigh = santaSleigh.cargoSize();
        elf.run();
        assertEquals(packetsOnSleigh + 1, santaSleigh.cargoSize());
    }

    @Test
    void elfCanPickAndDeliverMultiplePacketsInSequence() {
        final long packetsToDeliver = 10;
        long packetsOnSleigh = santaSleigh.cargoSize();
        for (long n = 1; n <= packetsToDeliver; n += 1) {
            elf.run();
        }
        assertEquals(packetsOnSleigh + packetsToDeliver, santaSleigh.cargoSize());
    }
}