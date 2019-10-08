package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {
    private final String FAMILY = "Miller";
    private final Present PRESENT = new Present(FAMILY);
    private Present dropResult;
    private Elf callbackResult;

    @Test
    void elf_to_string_returns_friendly_string() {
        Elf elf = new Elf(this::loader, this::dropper, this::callback);
        elf.run();
        String s = callbackResult.toString();
        assertEquals(elf.toString(), s);
        assertTrue(s.startsWith("Elf"));
    }

    @Test
    void elf_can_take_and_deliver_present() {
        Elf elf = new Elf(this::loader, this::dropper, this::callback);
        elf.run();
        assertEquals(PRESENT, dropResult);
        assertEquals(elf, callbackResult);
    }

    @Test
    void elf_throws_exception_if_receiving_empty_present() {
        Elf elf = new Elf(this::nullLoader, this::dropper, this::callback);
        assertThrows(NullPointerException.class, elf::run);
    }

    @SuppressWarnings("SameReturnValue")
    private Present nullLoader() {
        return null;
    }

    private void callback(Elf elf) {
        callbackResult = elf;
    }

    private void dropper(Present present) {
        dropResult = present;
    }

    private Present loader() {
        return PRESENT;
    }
}