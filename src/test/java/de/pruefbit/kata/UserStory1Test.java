package de.pruefbit.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static de.pruefbit.kata.WorkPlan.DEFAULT_PRODUCTION_LIMIT;
import static org.junit.jupiter.api.Assertions.*;

class UserStory1Test {
    private static final int PRODUCTION_LIMIT = 3;
    private static final List<String> FAMILIES = Arrays.asList("McFly", "Kojak", "Miller");

    private BlockingQueue<String> cargoList;
    private SantasSleigh theSleigh;

    @BeforeEach
    void setupEach() {
        cargoList = new LinkedBlockingQueue<>();
        theSleigh = new Sleigh(cargoList);
    }

    @Test
    void one_toy_machine_runs_with_one_elf_on_defaults() {
        ToyMachine tm = new ToyMachine();
        Elf elf = new Elf(tm::givePresent, theSleigh::pack, this::callback);
        elf.run();
        assertEquals(DEFAULT_PRODUCTION_LIMIT, cargoList.size());
    }

    @Test
    void one_toy_machine_runs_with_one_elf_with_no_fam_plan() {
        ProductionPlan pp = new ProductionPlan();
        pp.setProductionLimit(PRODUCTION_LIMIT);
        ToyMachine tm = new ToyMachine(pp);
        Elf elf = new Elf(tm::givePresent, theSleigh::pack, this::callback);
        elf.run();
        assertEquals(PRODUCTION_LIMIT, cargoList.size());
    }

    @Test
    void one_to_one_works_with_a_family_plan() {
        ProductionPlan pp = new ProductionPlan();
        FAMILIES.forEach(f -> pp.setProductionLimit(f, PRODUCTION_LIMIT));
        ToyMachine tm = new ToyMachine(pp);
        Elf elf = new Elf(tm::givePresent, theSleigh::pack, this::callback);
        elf.run();
        assertEquals(PRODUCTION_LIMIT * FAMILIES.size(), cargoList.size());
    }

    private void callback(Elf elf) {
        elf.run();
    }
}
