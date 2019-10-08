package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class UserStory1Test {
    private static final int PRODUCTION_LIMIT = 10;

    @Test
    void one_toy_machine_runs_with_one_elf_on_defaults() {
        BlockingQueue<String> cargoList = new LinkedBlockingQueue<>();
        SantasSleigh theSleigh = new Sleigh(cargoList);
        ToyMachine tm = new ToyMachine();
        Elf elf = new Elf(tm::givePresent, theSleigh::pack, this::callback);
        elf.run();
        assertEquals(ToyMachine.DEFAULT_PRODUCTION_LIMIT, cargoList.size());
    }

    @Test
    void one_toy_machine_runs_with_one_elf_with_no_fam_plan() {
        BlockingQueue<String> cargoList = new LinkedBlockingQueue<>();
        SantasSleigh theSleigh = new Sleigh(cargoList);
        ProductionPlan pp = new ProductionPlan();
        pp.setProductionLimit(PRODUCTION_LIMIT);
        ToyMachine tm = new ToyMachine(pp);
        Elf elf = new Elf(tm::givePresent, theSleigh::pack, this::callback);
        elf.run();
        assertEquals(PRODUCTION_LIMIT, cargoList.size());
    }

    private void callback(Elf elf) {
        elf.run();
    }
}
