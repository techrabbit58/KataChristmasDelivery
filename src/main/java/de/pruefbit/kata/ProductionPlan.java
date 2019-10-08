package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.pruefbit.kata.Helpers.*;
import static java.util.stream.Collectors.toList;

class ProductionPlan {
    private Map<String, Integer> families;
    private int genericProductionLimit;

    void addFamily(String name) {
        if (families == null) {
            families = new HashMap<>();
        }
        families.compute(mustNotBeEmpty(name), (k, v) -> (v == null) ? 1 : v + 1);
    }

    List<String> getFamilies() {
        return new ArrayList<>(families.keySet());
    }

    int getProductionLimit(String family) {
        return families.getOrDefault(mustNotBeEmpty(family), 0);
    }

    void setProductionLimit(String family, int limit) {
        families.put(mustNotBeEmpty(family), mustBePositive(limit));
    }

    void addAllFamilies(List<String> names) {
        mustNotBeEmpty(names).forEach(this::addFamily);
    }

    void setProductionLimit(int limit) {
        genericProductionLimit = mustBePositive(limit);
    }

    int getProductionLimit() {
        return genericProductionLimit;
    }
}