package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentTest {

    @Test
    void can_instantiate_one_new_present() {
        assertNotEquals(null, new Present("Default"));
    }
}