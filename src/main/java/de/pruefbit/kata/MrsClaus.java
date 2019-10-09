package de.pruefbit.kata;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static de.pruefbit.kata.WorkPlan.DEFAULT_PRODUCTION_LIMIT;
import static de.pruefbit.kata.WorkPlan.DEFAULT_TEAM_SIZE;

class MrsClaus implements Runnable {
    private static final String DEFAULT_FAMILY = "Default";

    private final SantasSleigh theSleigh = new Sleigh();
    private int teamSize;
    private int productionLimit;
    private List<String> families;
    private Map<String, BlockingDeque<Present>> queues = new HashMap<>();
    private List<ToyMachine> toyMachines = new ArrayList<>();
    private BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();

    MrsClaus() {
        this.teamSize = DEFAULT_TEAM_SIZE;
        this.productionLimit = DEFAULT_PRODUCTION_LIMIT;
        this.families = null;
    }

    MrsClaus(WorkPlan workPlan) {
        this.teamSize = workPlan.getTeamSize();
        this.productionLimit = workPlan.getProductionLimit();
        this.families = workPlan.getFamilies();
    }

    @Override
    public void run() {
        setup();
        loop();
    }

    private void setup() {
        if (families == null) {
            ProductionPlan pp = new ProductionPlan();
            pp.setProductionLimit(DEFAULT_FAMILY, productionLimit);
            for (int n = 0; n < teamSize; n += 1) {
                toyMachines.add(new ToyMachine(pp));
                availableElves.add(new Elf(this::givePresent, theSleigh::pack, this::callback));
            }
            queues.put(DEFAULT_FAMILY, new LinkedBlockingDeque<>());
        }
        else {
            throw new NotImplementedException();
        }
    }

    private void callback(Elf elf) {

    }

    private Present givePresent() {
        return null;
    }

    private void loop() {
    }
}
