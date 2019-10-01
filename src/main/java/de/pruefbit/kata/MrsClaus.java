package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static de.pruefbit.kata.Helpers.mustBePositive;

class MrsClaus implements Runnable {
    static private final SantasSleigh santasSleigh = new Sleigh();

    private final Executor execute;
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private final BlockingDeque<Present> availablePresents = new LinkedBlockingDeque<>();

    MrsClaus(int numberOfTeamMembers) {
        for (int n = 0; n < mustBePositive(numberOfTeamMembers); n += 1) {
            toyMachines.add(new ToyMachine());
            availableElves.add(new Elf(this::givePresent, santasSleigh::pack, this::callback));
        }
        execute = Executors.newFixedThreadPool(numberOfTeamMembers);
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
                execute.execute(availableElves.takeFirst());
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            presentsToDeliver -= 1;
        }
    }
}
