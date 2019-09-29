package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

class SantaSleighTest {

    private static final SantasSleigh santasSleigh = new Sleigh();
    private static final int MULTIPLE_PACKETS = 100;

    @Test
    void can_put_one_new_present_to_santas_sleigh() {
        Present present = new Present();
        santasSleigh.pack(present);
    }

    @Test
    void can_put_multiple_new_presents_to_santas_sleigh() {
        for (int i = 0; i < MULTIPLE_PACKETS; i += 1) {
            santasSleigh.pack(new Present());
        }
    }
}