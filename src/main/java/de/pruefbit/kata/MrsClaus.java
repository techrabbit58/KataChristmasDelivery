package de.pruefbit.kata;

class MrsClaus implements Runnable {
    static final int DEFAULT_TEAM_SIZE = 1;

    private final int teamSize;

    MrsClaus() {
        this.teamSize = DEFAULT_TEAM_SIZE;
    }

    @Override
    public void run() {
        setup();
        loop();
    }

    private void setup() {
    }

    private void loop() {
    }
}
