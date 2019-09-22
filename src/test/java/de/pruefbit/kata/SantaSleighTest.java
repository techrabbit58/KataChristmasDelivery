package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SantaSleighTest {

    private static final SantaSleigh santaSleigh = SantaSleigh.getSleigh();

    @Test
    void twoReferencesOfSantaSleighPointToIdenticalObject() {
        assertSame(SantaSleigh.getSleigh(), SantaSleigh.getSleigh());
    }

    @Test
    void canPutOneNewPresentToSantaSleigh() {
        Present present = new Present();
        long size = santaSleigh.cargoSize();
        santaSleigh.pack(present);
        assertEquals(size + 1, santaSleigh.cargoSize());
    }

    @Test
    void canPutTenNewPresentsToSantaSleigh() {
        long size = santaSleigh.cargoSize();
        for (int i = 0; i < 10; i += 1) {
            santaSleigh.pack(new Present());
        }
        assertEquals(size + 10, santaSleigh.cargoSize());
    }
}