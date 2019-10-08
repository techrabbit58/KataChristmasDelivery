package de.pruefbit.kata;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * ToyMachines give back presents on demand, as long as there are presents
 * to be produced, according to the machine setup.
 * ToyMachines work only on demand, but never self-triggered.
 * If set up accordingly (with a list of families and a production limit
 * per family), ToyMachines will not give new presents, when all limits for
 * all families got exceeded. Instead, "null" will be returned.
 * ToyMachines do not guarantee delivery of all packets for one family in a row.
 * The production of presents for multiple families may be interleaved.
 * ToyMachines always obey a ProductionPlan. If made without a ProductionPlan,
 * the machines will make their own default ProductionPlan.
 *
 * @see ProductionPlan
 */
class ToyMachine {
    static final int DEFAULT_PRODUCTION_LIMIT = 3;

    private int genericProductionLimit;
    private final Map<String, Integer> families;

    ToyMachine() {
        genericProductionLimit = DEFAULT_PRODUCTION_LIMIT;
        families = null;
    }

    ToyMachine(ProductionPlan productionPlan) {
        genericProductionLimit = productionPlan.getProductionLimit();
        List<String> ppf = productionPlan.getFamilies();
        if (ppf != null) {
            families = new HashMap<>();
            ppf.forEach(f -> families.put(f, productionPlan.getProductionLimit(f)));
        }
        else {
            families = null;
        }
    }

    Present givePresent() {
        if (families != null) {
            return giveFamilyPresent();
        }
        else {
            return giveGenericPresent();
        }
    }

    private Present giveFamilyPresent() {
        List<String> eligibleFamilies = families.keySet().stream()
                .filter(f -> (families.get(f) > 0))
                .collect(toList());
        if (!eligibleFamilies.isEmpty()) {
            Collections.shuffle(eligibleFamilies);
            String ef = eligibleFamilies.get(0);
            families.compute(ef, (k, v) -> (v == null || v == 0) ? 0 : v - 1);
            return new Present(ef);
        }
        return null;
    }

    private Present giveGenericPresent() {
        Present present;
        if (genericProductionLimit > 0) {
            present = new Present();
            genericProductionLimit -= 1;
        }
        else {
            present = null;
        }
        return present;
    }
}
