package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {

    private static final long MULTIPLE_PACKETS = 100;
    static private Elf elf;
    static private boolean isElfFreeForNextJob;

    static void callback(Object o) {
        isElfFreeForNextJob = true;
    }

    @BeforeAll
    static void setup() {
        ToyMachine toyMachine = new ToyMachine();
        SantasSleigh santasSleigh = new Sleigh();
        elf = new Elf(toyMachine::givePresent, santasSleigh::pack, ElfTest::callback);
    }

    @Test
    void could_instantiate_one_elf_with_dependencies() {
        assertNotEquals(null, elf);
    }

    @Test
    void elf_when_instantiated_with_nulls_throws_exception() {
        assertThrows(NullPointerException.class, () -> new Elf(null, null, null));
    }

    @Test
    void elf_picks_and_delivers_one_present_to_santas_sleigh() {
        isElfFreeForNextJob = false;
        elf.run();
        assertTrue(isElfFreeForNextJob);
    }

    @Test
    void elf_can_pick_and_deliver_multiple_packets_in_sequence() {
        for (long n = 1; n <= MULTIPLE_PACKETS; n += 1) {
            isElfFreeForNextJob = false;
            elf.run();
            assertTrue(isElfFreeForNextJob);
        }
    }
}