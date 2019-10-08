package de.pruefbit.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToyMachineTest {
    private static final int PRODUCTION_LIMIT = 3;
    private static final List<String> FAMILIES = Arrays.asList("Miller", "Kojak", "McFly");

    @Test
    void works_with_defaults_if_plan_not_given() {
        ToyMachine tm = new ToyMachine();
        assertEquals(ToyMachine.DEFAULT_PRODUCTION_LIMIT, getProductionResult(tm));
    }

    @Test
    void works_with_non_family_plan_if_given() {
        ProductionPlan pp = new ProductionPlan();
        pp.setProductionLimit(PRODUCTION_LIMIT);
        ToyMachine tm = new ToyMachine(pp);
        assertEquals(PRODUCTION_LIMIT, getProductionResult(tm));
    }

    @Test
    void works_with_family_plan_if_given() {
        ProductionPlan pp = new ProductionPlan();
        FAMILIES.forEach(f -> pp.setProductionLimit(f, PRODUCTION_LIMIT));
        ToyMachine tm = new ToyMachine(pp);
        assertEquals(PRODUCTION_LIMIT * FAMILIES.size(), getProductionResult(tm));
    }

    private int getProductionResult(ToyMachine tm) {
        int counter = 0;
        Present present = tm.givePresent();
        while (present != null) {
            counter += 1;
            present = tm.givePresent();
        }
        return counter;
    }
}