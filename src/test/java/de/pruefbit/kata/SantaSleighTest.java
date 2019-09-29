package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

class SantaSleighTest {

    private static final SantasSleigh santasSleigh = new SantasSleigh();
    private static final int MULTIPLE_PACKETS = 100;

    @Test
    void canPutOneNewPresentToSantaSleigh() {
        Present present = new Present();
        santasSleigh.pack(present);
    }

    @Test
    void canPutMultipleNewPresentsToSantaSleigh() {
        for (int i = 0; i < MULTIPLE_PACKETS; i += 1) {
            santasSleigh.pack(new Present());
        }
    }
}