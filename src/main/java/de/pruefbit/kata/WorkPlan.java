package de.pruefbit.kata;

import static de.pruefbit.kata.Helpers.mustBePositive;

class WorkPlan {
    static final int DEFAULT_PRODUCTION_LIMIT = 3;
    static final int DEFAULT_TEAM_SIZE = 1;

    private int teamSize = DEFAULT_TEAM_SIZE;

    private int productionLimit = DEFAULT_PRODUCTION_LIMIT;

    void setTeamSize(int teamSize) {
        this.teamSize = mustBePositive(teamSize);
    }

    int getTeamSize() {
        return teamSize;
    }

    void setProductionLimit(int productionLimit) {
        this.productionLimit = productionLimit;
    }

    int getProductionLimit() {
        return productionLimit;
    }
}
