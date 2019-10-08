package de.pruefbit.kata;

import static de.pruefbit.kata.Helpers.mustBePositive;
import static de.pruefbit.kata.MrsClaus.DEFAULT_TEAM_SIZE;

class WorkPlan {
    private int teamSize = DEFAULT_TEAM_SIZE;

    void setTeamSize(int teamSize) {
        this.teamSize = mustBePositive(teamSize);
    }

    int getTeamSize() {
        return teamSize;
    }
}
