package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class SantaSleighTest {

    @Test
    void can_instantiate_sleigh_without_logging() {
        SantasSleigh theSleigh = new Sleigh();
        theSleigh.pack(new Present("Miller"));
        assertNotNull(theSleigh);
    }

    @Test
    void can_put_new_presents_to_santas_sleigh() {
        BlockingQueue<String> cargoList = new LinkedBlockingQueue<>();
        SantasSleigh theSleigh = new Sleigh(cargoList);
        List<String> families = Arrays.asList("Miller", "Kojak", "McFly");
        for (String family : families) {
            theSleigh.pack(new Present(family));
            assertTrue(cargoList.toArray()[cargoList.size() - 1].toString().contains(family));
        }
        assertEquals(families.size(), cargoList.size());
    }
}