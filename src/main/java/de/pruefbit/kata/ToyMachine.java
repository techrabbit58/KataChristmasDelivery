package de.pruefbit.kata;

import java.util.*;
import java.util.stream.Collectors;

import static de.pruefbit.kata.Helpers.mustNotBeNegative;

class ToyMachine {
    private final Map<String, Integer> families = new HashMap<>();

    ToyMachine() {
    }

    Present givePresent() {
        Present present;
        if (families.isEmpty()) {
            present = new Present("Default");
        } else {
            String family = pickOneFamily();
            if (family == null) {
                return null;
            }
            present = new Present(family);
            families.put(family, families.get(family) - 1);
        }
        return present;
    }

    private String pickOneFamily() {
        List<String> eligibleFamilies = families.entrySet().stream()
                .filter(entry -> (entry.getValue() > 0))
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
            toyMachine.families.compute(
                    Objects.requireNonNull(name),
                    (k, v) -> v == null ? 1 : v + 1
            );
            return this;
        }

        Builder setMaxPresentsPerFamily(String name, int n) {
            toyMachine.families.compute(
                    Objects.requireNonNull(name),
                    (k, v) -> mustNotBeNegative(n));
            return this;
        }

        ToyMachine build() {
            return this.toyMachine;
        }
    }
}
