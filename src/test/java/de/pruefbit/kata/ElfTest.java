package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {

    static private Elf elf;
    static private boolean isElfFreeForNextJob;

    static void callback() {
        isElfFreeForNextJob = true;
    }

    @BeforeAll
    static void setup() {
        ToyMachine toyMachine = new ToyMachine();
        SantasSleigh santasSleigh = new SantasSleigh();
        elf = new Elf(toyMachine::givePresent, santasSleigh::pack, ElfTest::callback);
    }

    @Test
    void could_instantiate_one_elf_with_dependencies() {
        assertNotEquals(null, elf);
    }

    @Test
    void elf_picks_and_delivers_one_present_to_santas_sleigh() {
        isElfFreeForNextJob = false;
        elf.run();
        assertTrue(isElfFreeForNextJob);
    }

    @Test
    void elf_can_pick_and_deliver_multiple_packets_in_sequence() {
        long MULTIPLE_PACKETS = 100;
        for (long n = 1; n <= MULTIPLE_PACKETS; n += 1) {
            isElfFreeForNextJob = false;
            elf.run();
            assertTrue(isElfFreeForNextJob);
        }
    }
}