package de.pruefbit.kata;

import java.util.*;
import java.util.stream.Collectors;

import static de.pruefbit.kata.Helpers.mayNotBeNegative;

class ToyMachine {
    private int maxPresentsPerFamily;
    private final Map<String, Integer> families = new HashMap<>();

    ToyMachine() {
        maxPresentsPerFamily = 0;
    }

    Present givePresent() {
        Present present;
        if (families.isEmpty()) {
            present = new Present();
        } else {
            String family = pickOneFamily();
            if (family == null) {
                return null;
            }
            present = new Present(family);
            families.put(family, families.get(family) + 1);
        }
        return present;
    }

    private String pickOneFamily() {
        List<String> eligibleFamilies = families.entrySet().stream()
                .filter(entry -> (entry.getValue() < maxPresentsPerFamily))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (eligibleFamilies.isEmpty()) {
            return null;
        }
        Collections.shuffle(eligibleFamilies);
        return eligibleFamilies.get(0);
    }

    static class Builder {

        private final ToyMachine toyMachine;

        Builder() {
            this.toyMachine = new ToyMachine();
        }

        Builder addFamily(String name) {
            toyMachine.families.putIfAbsent(Objects.requireNonNull(name, "family name is required"), 0);
            return this;
        }

        Builder setMaxPresentsPerFamily(int n) {
            toyMachine.maxPresentsPerFamily = mayNotBeNegative(n);
            return this;
        }

        ToyMachine build() {
            if (!toyMachine.families.isEmpty() && toyMachine.maxPresentsPerFamily < 1) {
                throw new RuntimeException("toy machines with families assigned need max presents values");
            }
            return this.toyMachine;
        }
    }
}
