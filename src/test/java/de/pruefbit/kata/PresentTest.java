package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentTest {

    @Test
    void canInstantiateOneNewPresent() {
        assertNotEquals(null, new Present());
    }
}