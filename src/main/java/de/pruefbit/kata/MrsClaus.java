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
    static private final List<String> families = new ArrayList<>();
    static private Supplier<ToyMachine> toyMachineSupplier = MrsClaus::defaultToyMachineSupplier;
    static private int maxPresentsPerFamily = 0;

    private final Executor execute;
    private final List<ToyMachine> toyMachines = new ArrayList<>();
    private final BlockingDeque<Elf> availableElves = new LinkedBlockingDeque<>();
    private final BlockingDeque<Present> availablePresents = new LinkedBlockingDeque<>();

    MrsClaus(int numberOfTeamMembers) {
        mustBePositive(numberOfTeamMembers);
        for (int n = 0; n < numberOfTeamMembers; n += 1) {
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
        builder.setMaxPresentsPerFamily(maxPresentsPerFamily);
        return builder.build();
    }

    private static int mustBePositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("number of team members must be positive");
        }
        return n;
    }

    private static int mayNotBeNegative(int n) {
        if (n < 0) {
            throw new RuntimeException("number may not be negative");
        }
        return n;
    }

    private static List<String> mustNotBeEmpty(List<String> stringList) {
        if (stringList.isEmpty()) {
            throw new RuntimeException("list of strings must not be empty");
        }
        return stringList;
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
            Present p = tm.givePresent();
            if (p == null) {
                break;
            }
            availablePresents.add(p);
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

    static class Builder {

        private int numberOfTeamMembers = 0;

        Builder setNumberOfTeamMembers(int teamSize) {
            this.numberOfTeamMembers = mustBePositive(teamSize);
            return this;
        }

        Builder addFamilies(List<String> familyNames) {
            Objects.requireNonNull(families);
            families.addAll(mustNotBeEmpty(familyNames));
            toyMachineSupplier = MrsClaus::familyToyMachineSupplier;
            return this;
        }

        Builder setMaxPresentsPerFamily(int maxPresents) {
            maxPresentsPerFamily = mayNotBeNegative(maxPresents);
            return this;
        }

        MrsClaus build() {
            return new MrsClaus(numberOfTeamMembers);
        }
    }
}
