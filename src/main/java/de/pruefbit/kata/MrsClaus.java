package de.pruefbit.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

class MrsClaus implements Runnable {
    static private final SantasSleigh santasSleigh = new Sleigh();
    static private List<String> families;
    static private Supplier<ToyMachine> toyMachineSupplier = MrsClaus::defaultToyMachineSupplier;
    static private List<Integer> maxPerMachine;
    static private int currentToyMachine;

    private final Executor execute;
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private final BlockingDeque<Present> availablePresents = new LinkedBlockingDeque<>();

    private MrsClaus(int numberOfTeamMembers) {
        Helpers.mustBePositive(numberOfTeamMembers);
        for (int n = 0; n < numberOfTeamMembers; n += 1) {
            currentToyMachine = n;
            toyMachines.add(toyMachineSupplier.get());
            availableElves.add(new Elf(this::givePresent, santasSleigh::pack, this::callback));
        }
        execute = Executors.newFixedThreadPool(numberOfTeamMembers);
    }

    private static ToyMachine defaultToyMachineSupplier() {
        return new ToyMachine();
    }

    private static ToyMachine familyToyMachineSupplier() {
        ToyMachine.Builder builder = new ToyMachine.Builder();
        families.forEach(builder::addFamily);
        builder.setMaxPresentsPerFamily(maxPerMachine.get(currentToyMachine));
        return builder.build();
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
        boolean terminate = false;
        while (!terminate) {
            int presentsToDeliver = 0;
            for (ToyMachine tm : toyMachines) {
                Present p = tm.givePresent();
                if (p == null) {
                    continue;
                }
                availablePresents.add(p);
                presentsToDeliver += 1;
            }
            terminate = presentsToDeliver < 1;
            while (presentsToDeliver > 0) {
                try {
                    execute.execute(availableElves.takeFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException("mrs claus got interrupted");
                }
                presentsToDeliver -= 1;
            }
            if (families.isEmpty()) {
                break;
            }
        }
    }

    static class Builder {

        private int numberOfTeamMembers;

        Builder() {
            toyMachineSupplier = MrsClaus::defaultToyMachineSupplier;
            families = new ArrayList<>();
            maxPerMachine = new ArrayList<>();
            numberOfTeamMembers = 1;
        }

        Builder setNumberOfTeamMembers(int teamSize) {
            this.numberOfTeamMembers = Helpers.mustBePositive(teamSize);
            for (int n = 0; n < numberOfTeamMembers; n += 1) {
                maxPerMachine.add(0);
            }
            return this;
        }

        Builder addFamilies(List<String> familyNames) {
            Objects.requireNonNull(familyNames);
            families.addAll(Helpers.mustNotBeEmpty(familyNames));
            toyMachineSupplier = MrsClaus::familyToyMachineSupplier;
            return this;
        }

        Builder setMaxPresentsPerFamily(int maxPresents) {
            int maxPresentsPerFamily = Helpers.mustNotBeNegative(maxPresents);
            for (int n = 0; n < maxPresentsPerFamily; n += 1) {
                int index = n % maxPerMachine.size();
                maxPerMachine.set(index, maxPerMachine.get(index) + 1);
            }
            return this;
        }

        MrsClaus build() {
            return new MrsClaus(numberOfTeamMembers);
        }
    }
}
