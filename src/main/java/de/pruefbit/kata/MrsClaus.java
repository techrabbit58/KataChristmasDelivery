package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

class MrsClaus implements Runnable {

    static private final SantasSleigh santasSleigh = new Sleigh();
    private final Executor execute;
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private final BlockingDeque<Present> availablePresents = new LinkedBlockingDeque<>();

    MrsClaus(int numberOfTeamMembers) {
        if (numberOfTeamMembers <= 0) {
            throw new IllegalArgumentException("number of team members must be positive");
        }
        for (int n = 0; n < numberOfTeamMembers; n += 1) {
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
            e.printStackTrace();
            throw new RuntimeException("an elf could not load a present");
        }
    }

    @Override
    public void run() {
        for (ToyMachine tm : toyMachines) {
            availablePresents.add(tm.givePresent());
        }
        int presentsToDeliver = availablePresents.size();
        while (presentsToDeliver > 0) {
            try {
                execute.execute(availableElves.takeFirst());
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("mrs claus got interrupted");
            }
            presentsToDeliver -= 1;
        }
    }
}
