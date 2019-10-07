package de.pruefbit.kata;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static de.pruefbit.kata.Helpers.mustBePositive;

class MrsClaus implements Runnable {
    static private final SantasSleigh santasSleigh = new Sleigh();

    private final ExecutorService exec;
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private final BlockingDeque<Present> availablePresents = new LinkedBlockingDeque<>();

    MrsClaus(int teamSize) {
        final Set<String> families = new HashSet<>();
        families.add("Default");
        final int maxPresentsPerFamily = 1;
        deployToyMachines(teamSize, families, maxPresentsPerFamily);
        employElves(teamSize);
        exec = Executors.newFixedThreadPool(teamSize);
    }

    private MrsClaus(OpMode opMode) {
        deployToyMachines(opMode.teamSize, opMode.families, 1);
        employElves(opMode.teamSize);
        exec = Executors.newFixedThreadPool(opMode.teamSize);
    }

    private void deployToyMachines(int teamSize, Set<String> families, int maxPresentsPerFamily) {
        for (int n = 0; n < mustBePositive(teamSize); n += 1) {
            ToyMachine.Builder tmBuilder = new ToyMachine.Builder();
            families.forEach(tmBuilder::addFamily);
            families.forEach(f -> tmBuilder.setMaxPresentsPerFamily(f, maxPresentsPerFamily));
            toyMachines.add(tmBuilder.build());
        }
    }

    private void employElves(int teamSize) {
        for (int n = 0; n < mustBePositive(teamSize); n += 1) {
            availableElves.add(new Elf(this::givePresent, santasSleigh::pack, this::callback));
        }
    }

    private void callback(Elf elf) {
        availableElves.addLast(elf);
    }

    private Present givePresent() {
        try {
            return availablePresents.takeFirst();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run() {
        int presentsToDeliver = 0;
        for (ToyMachine tm : toyMachines) {
            Present p = tm.givePresent();
            if (p == null) {
                continue;
            }
            availablePresents.add(p);
            presentsToDeliver += 1;
        }
        while (presentsToDeliver > 0) {
            try {
                exec.execute(availableElves.takeFirst());
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            presentsToDeliver -= 1;
        }
    }

    private static class OpMode {
        private final Set<String> families = new HashSet<>();
        private int teamSize;
    }

    static class Builder {
        private final OpMode opMode;

        Builder() {
            this.opMode = new OpMode();
            opMode.teamSize = 1;
        }

        Builder setTeamSize(int n) {
            opMode.teamSize = mustBePositive(n);
            return this;
        }

        Builder addFamily(String name) {
            opMode.families.add(name);
            return this;
        }

        MrsClaus build() {
            return new MrsClaus(opMode);
        }

    }
}
