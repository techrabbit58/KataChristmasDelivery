package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentTest {

    @Test
    void can_be_instantiated_for_unspecified_family() {
        assertNull(new Present().getFamily());
    }

    @Test
    void can_be_instantiated_for_the_millers() {
        assertEquals("Miller", new Present("Miller").getFamily());
    }

    @Test
    void can_identify_itself_by_a_friendly_string() {
        String family = "Miller";
        String s = new Present(family).toString();
        assertTrue(s.startsWith("Present") && s.contains(family));
    }
}