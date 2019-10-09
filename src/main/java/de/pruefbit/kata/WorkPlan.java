package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.List;

import static de.pruefbit.kata.Helpers.mustBePositive;
import static de.pruefbit.kata.Helpers.mustNotBeEmpty;

class WorkPlan {
    static final int DEFAULT_PRODUCTION_LIMIT = 3;
    static final int DEFAULT_TEAM_SIZE = 1;

    private int teamSize = DEFAULT_TEAM_SIZE;
    private int productionLimit = DEFAULT_PRODUCTION_LIMIT;
    private List<String> families = null;

    void setFamilies(List<String> families) {
        if (this.families == null) {
            this.families = new ArrayList<>();
        }
        mustNotBeEmpty(families).forEach(f -> this.families.add(mustNotBeEmpty(f)));
    }

    List<String> getFamilies() {
        return families;
    }

    void setTeamSize(int teamSize) {
        this.teamSize = mustBePositive(teamSize);
    }

    int getTeamSize() {
        return teamSize;
    }

    void setProductionLimit(int productionLimit) {
        this.productionLimit = mustBePositive(productionLimit);
    }

    int getProductionLimit() {
        return productionLimit;
    }
}
